/*
* Copyright 2024 - 2024 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* https://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.springframework.ai.mcp.sample.server;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import dto.AudienceTag;
import dto.AudiencesSearchResponse;
import dto.AudiencesType;
import dto.AudiencesTypesResponse;
import dto.EntitiesResponse;
import dto.EntityProperties;
import dto.TagProperties;
import dto.TagsSearchResponse;
import dto.TagsType;
import dto.TagsTypesResult;
import dto.TagsTypeResult;
import dto.TagsTypesResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Service	
public class InsightsService {

	    // Inject Spring's Environment to access env variables and properties
    @Autowired
    private Environment env;
	
	@Value("${qloo.base.url}")
	private String BASE_URL ;

	@Value("${qloo.api.key}")
	private String INSIGHTS_API_TOKEN ;

	private final RestClient restClient;

	// place, brand, media, people, album, artist, destination, podcast
	final Pattern placePattern = Pattern.compile("place", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	final Pattern brandPattern = Pattern.compile("brand", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	final Pattern mediaPattern = Pattern.compile("media", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	final Pattern peoplePattern = Pattern.compile("people", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	final Pattern personPattern = Pattern.compile("person", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	final Pattern albumPattern = Pattern.compile("album", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	final Pattern artistPattern = Pattern.compile("artist", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	final Pattern destinationPattern = Pattern.compile("destination", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	final Pattern podcastPattern = Pattern.compile("podcast", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);


	public InsightsService() {

		this.restClient = RestClient.builder()
			.baseUrl(BASE_URL)
			.defaultHeader("Accept", "application/geo+json")
			.defaultHeader("User-Agent", "NetworkApiClient/1.0 (your@email.com)")
			.build();
	}

	private String getAccessToken() {
		return INSIGHTS_API_TOKEN;
	}

	private String printProperties(EntityProperties properties) {
		StringBuilder sb = new StringBuilder();
		if (properties != null) {
			sb.append( properties.getAddress() != null?", Address: " + properties.getAddress():"");
			sb.append(properties.getPhone() != null? ", Phone: " + properties.getPhone():"");
			sb.append( properties.getBusinessRating() != null?", Business Rating: " + properties.getBusinessRating():"");
			sb.append(properties.getMenuUrl() != null? ", Menu Url: " + properties.getMenuUrl():"");

			sb.append( properties.getDescription() != null?", Description: " + properties.getDescription():"");
			sb.append(properties.getContentRating() != null? ", Rating: " + properties.getContentRating():"");
			sb.append(properties.getReleaseDate() != null?", Release Date: " + properties.getReleaseDate():"");
			sb.append(properties.getReleaseYear() != null?", Release Year: " + properties.getReleaseYear():"");
			sb.append(properties.getDuration() != null?", Duration: " + properties.getDuration():"");
			sb.append(properties.getImage() != null && properties.getImage().getUrl() != null ? ", Image URL: " + properties.getImage().getUrl() : "");
			sb.append(properties.getFilmingLocation() != null?", Filming Location: " + properties.getFilmingLocation():"");
			sb.append(properties.getWebsites() != null ? ", Websites: " + String.join(", ", properties.getWebsites()) : "");
			sb.append(properties.getProductionCompanies() != null ? ", Production Companies: " + String.join(", ", properties.getProductionCompanies()) : "");
			sb.append(properties.getReleaseCountry() != null ?", Release Country: " + String.join(", ", properties.getReleaseCountry()) : "");
			//if (properties.getAkas() != null) {
			//	properties.getAkas().forEach(aka -> sb.append("Aka: " + aka.getValue() + " (" + String.join(", ", aka.getLanguages()) + ")"));
			//}
		} else {
			sb.append("No properties available");
		}
		return sb.toString();
	}

	/**
	 * recommendations search for entities (place, media, brand, people, ...) in specific latitude/longitude either similar either meeting special criteria
	 * @param type the type of entity to search for, e.g. "place", "media", "person
	 * @param location in latitude/longitude format, e.g. "48.8588443,2.2943506"expensive
	 * @param filterTags optional tags to filter the results, e.g urn:tag:accessibility:place:wheelchair_accessible_entrance, urn:tag:offerings:place:vegan_options,urn:tag:dining_options,...
	 * @param signals optional similarity signals as dictionary of key/value pairs, e.g. {"entities": "SOME_ENTITY_ID", "tags": "urn:tag:SOME_TAG"}
	 * @return the entities found
	 * @throws RestClientException if the request fails
	 */
	@Tool(description = "recommendations search for entities (places, media, people, album, artist, destination, podcast, ...) in specific latitude/longitude either similar either meeting special criteria")
	public String recommendEntities(String type, String location, @Nullable ArrayList<String> filterTags, @Nullable Map<String, String> signals) {
		//System.out.println("recommend Entities for " + type + " at location: " + location + " with filterTags: " + filterTags + " and signals: " + signals);
//		String token = env.getProperty("NETWORK_API_KEY", "SOME_TOKEN");
		String token = getAccessToken(); // Get the access token from the environment or generate it
		//System.out.println(token);
		if (location == null || location.isEmpty()) {
			throw new IllegalArgumentException("Location must not be null or empty");
		}
		UriBuilder uriBuilder = UriComponentsBuilder.fromUriString(BASE_URL + "v2/insights/");
		location = location.trim().toLowerCase();
		if (location. startsWith("point") || location.startsWith("polygon") || location.startsWith("line") || location.startsWith("circle") || location.startsWith("multipolygon") || location.startsWith("urn:entity"))
			uriBuilder.queryParam("filter.location.query", location);
		else
			uriBuilder.queryParam("filter.location.query", location);
		if  (placePattern.matcher(type).find())
			uriBuilder.queryParam("filter.type","urn:entity:place");
		else
		if  (mediaPattern.matcher(type).find())
			uriBuilder.queryParam("filter.type", "urn:entity:media");
		else
		if  (personPattern.matcher(type).find())
			uriBuilder.queryParam("filter.type", "urn:entity:person");
		else
		if (brandPattern.matcher(type).find())
			uriBuilder.queryParam("filter.type", "urn:entity:brand");
		else
		if (artistPattern.matcher(type).find())
			uriBuilder.queryParam("filter.type", "urn:entity:artist");
		else
		if (albumPattern.matcher(type).find())
			uriBuilder.queryParam("filter.type", "urn:entity:album");
		else 
		if (destinationPattern.matcher(type).find())
			uriBuilder.queryParam("filter.type", "urn:entity:destination");
		else 
		if (podcastPattern.matcher(type).find())
			uriBuilder.queryParam("filter.type", "urn:entity:podcast");
		else
			throw new IllegalArgumentException("Unsupported type: " + type);
		if (filterTags != null && !filterTags.isEmpty()) {
			uriBuilder.queryParam("filter.tags", String.join(",", filterTags));
			uriBuilder.queryParam("operator.filter.tags", "intersection");
		}
		if (signals != null && !signals.isEmpty()) {
			String signalType = signals.get("type");
			String signalsValue = signals.get("value");
			switch (signalType) {
				case "entities":
					uriBuilder.queryParam("signal.interests.entities", signalsValue);
					break;
				case "tags":
					uriBuilder.queryParam("signal.interests.tags",signalsValue);
					break;
				case "location":
					uriBuilder.queryParam("signal.location", signalsValue);
					break;
				default:
					throw new IllegalArgumentException("Unsupported signal type: " + signals);
			}
		}
		// set number of results by default to 1
		uriBuilder.queryParam("take",1);
		String uri = uriBuilder.build().toString();
		System.out.println("Request URI: " + uri);
		ResponseEntity<EntitiesResponse> entitiesResponse = restClient.get()
  		.uri(uri)
		.header("x-api-key", token)
		.header("Cache-Control", "no-cache")
		.header("Authorization", "Bearer "+ token)
		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	  	.header("Accept", "application/json")
		.header("Connection", "keep-alive")
		.header("Accept-Encoding", "gzip, deflate, br")
		.header("Accept", "*/*")

  		.retrieve()
  		.toEntity(EntitiesResponse.class);
		
		String data = String.format("Name: %s, ID: %s, Type: %s, SubType: %s Properties: %s",
				entitiesResponse.getBody().getEntitiesResult().getEntities().get(0).getName(),
				entitiesResponse.getBody().getEntitiesResult().getEntities().get(0).getEntityId(),
				entitiesResponse.getBody().getEntitiesResult().getEntities().get(0).getType(),
				entitiesResponse.getBody().getEntitiesResult().getEntities().get(0).getSubType(),
				printProperties(entitiesResponse.getBody().getEntitiesResult().getEntities().get(0).getProperties()));
		return data;
	}

	/**
	 * get tags types 
	 * No parameters required
	 * @return the tags types found
	 * @throws RestClientException if the request fails
	 */
	@Tool(description = "get Tags Types")
	public String getTagsTypes() {
		//System.out.println("recommend Entities for " + type + " at location: " + location + " with filterTags: " + filterTags + " and signals: " + signals);
//		String token = env.getProperty("NETWORK_API_KEY", "SOME_TOKEN");
		String token = getAccessToken(); // Get the access token from the environment or generate it
		//System.out.println(token);
		UriBuilder uriBuilder = UriComponentsBuilder.fromUriString(BASE_URL + "v2/tags/types");
		// set number of results by default to 3
		uriBuilder.queryParam("take",3);
		String uri = uriBuilder.build().toString();
		System.out.println("Request URI: " + uri);
		ResponseEntity<TagsTypesResponse> tagsTypesResponse = restClient.get()
  		.uri(uri)
		.header("x-api-key", token)
		.header("Cache-Control", "no-cache")
		.header("Authorization", "Bearer "+ token)
		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	  	.header("Accept", "application/json")
		.header("Connection", "keep-alive")
		.header("Accept-Encoding", "gzip, deflate, br")
		.header("Accept", "*/*")

  		.retrieve()
  		.toEntity(TagsTypesResponse.class);
		
		String data = String.format("Type: %s, Parents: %s",
				tagsTypesResponse.getBody().getTagsTypesResult().getTagsTypes().get(0).getType(),
				printTagParents(tagsTypesResponse.getBody().getTagsTypesResult().getTagsTypes().get(0).getParents()));
		return data;
	}

	/**
	 * search tags 
	 * @param query the type of tag to search for, e.g. "vegan"
	 * @return the tags  found
	 * @throws RestClientException if the request fails
	 */
	@Tool(description = "search Tags filtered by query")
	public String searchTags(String query) {
		String token = getAccessToken(); // Get the access token from the environment or generate it
		//System.out.println(token);
		UriBuilder uriBuilder = UriComponentsBuilder.fromUriString(BASE_URL + "v2/tags/");
		uriBuilder.queryParam("filter.query", query);
		// set number of results by default to 1
		uriBuilder.queryParam("take",1);
		String uri = uriBuilder.build().toString();
		System.out.println("Request URI: " + uri);
		ResponseEntity<TagsSearchResponse> tagsSearchResponse = restClient.get()
  		.uri(uri)
		.header("x-api-key", token)
		.header("Cache-Control", "no-cache")
		.header("Authorization", "Bearer "+ token)
		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	  	.header("Accept", "application/json")
		.header("Connection", "keep-alive")
		.header("Accept-Encoding", "gzip, deflate, br")
		.header("Accept", "*/*")

  		.retrieve()
  		.toEntity(TagsSearchResponse.class);
		
		String data = String.format("Type: %s, Parents: %s Id: %s Name:%s Popularity: %s Properties: %s",
				tagsSearchResponse.getBody().getTagsSearchResult().getTags().get(0).getType(),
				printTagParents(tagsSearchResponse.getBody().getTagsSearchResult().getTags().get(0).getParents()),
				tagsSearchResponse.getBody().getTagsSearchResult().getTags().get(0).getId(),
				tagsSearchResponse.getBody().getTagsSearchResult().getTags().get(0).getName(),
				tagsSearchResponse.getBody().getTagsSearchResult().getTags().get(0).getPopularity(),
				printTagProperties(tagsSearchResponse.getBody().getTagsSearchResult().getTags().get(0).getProperties())
				);
		return data;
	}

	private String printTagProperties(TagProperties properties) {
		StringBuilder sb = new StringBuilder();
		if (properties != null) {
			sb.append(properties.getDescription() != null ? ", Description: " + properties.getDescription() : "");
			sb.append(properties.getHistory() != null && properties.getHistory() != null ? ", History: " + properties.getHistory() : "");
			sb.append(properties.getOrigin() != null ? ", Origin: " +properties.getOrigin() : "");
			sb.append(properties.getPresentationStyle() != null ? ", Presentation Style: " + properties.getPresentationStyle() : "");
		} else {
			sb.append("No properties available");
		}
		return sb.toString();
	}

	private String printTagParents(ArrayList<TagsType> parents) {
		StringBuilder sb = new StringBuilder();
		if (parents != null && !parents.isEmpty()) {
			for (TagsType parent : parents) {
				sb.append(parent.getType()).append(", ");
			}
			// Remove the last comma and space
			if (sb.length() > 0) {
				sb.setLength(sb.length() - 2);
			}
		} else {
			sb.append("No parents available");
		}
		return sb.toString();
	}

	/**
	 * get audiences types 
	 * No parameters required
	 * @return the audiences types found
	 * @throws RestClientException if the request fails
	 */
	@Tool(description = "get Audiences Types")
	public String getAudiencesTypes() {
		//System.out.println("recommend Entities for " + type + " at location: " + location + " with filterTags: " + filterTags + " and signals: " + signals);
//		String token = env.getProperty("NETWORK_API_KEY", "SOME_TOKEN");
		String token = getAccessToken(); // Get the access token from the environment or generate it
		//System.out.println(token);
		UriBuilder uriBuilder = UriComponentsBuilder.fromUriString(BASE_URL + "v2/audiences/types");
		// set number of results by default to 3
		uriBuilder.queryParam("take",3);
		String uri = uriBuilder.build().toString();
		System.out.println("Request URI: " + uri);
		ResponseEntity<AudiencesTypesResponse> audiencesTypesResponse = restClient.get()
  		.uri(uri)
		.header("x-api-key", token)
		.header("Cache-Control", "no-cache")
		.header("Authorization", "Bearer "+ token)
		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	  	.header("Accept", "application/json")
		.header("Connection", "keep-alive")
		.header("Accept-Encoding", "gzip, deflate, br")
		.header("Accept", "*/*")

  		.retrieve()
  		.toEntity(AudiencesTypesResponse.class);
		
		String data = String.format("Type: %s, Parents: %s",
				audiencesTypesResponse.getBody().getAudiencesTypesResult().getAudiencesTypes().get(0).getType(),
				printAudienceParents(audiencesTypesResponse.getBody().getAudiencesTypesResult().getAudiencesTypes().get(0).getParents()));
		return data;
	}

	private String printAudienceParents(ArrayList<AudiencesType> parents) {
		StringBuilder sb = new StringBuilder();
		if (parents != null && !parents.isEmpty()) {
			for (AudiencesType parent : parents) {
				sb.append(parent.getType()).append(", ");
			}
			// Remove the last comma and space
			if (sb.length() > 0) {
				sb.setLength(sb.length() - 2);
			}
		} else {
			sb.append("No parents available");
		}
		return sb.toString();
	}

	/**
	 * search audiences 
	 * @param query the type of audience to search for, e.g. "urn:audience:communities"
	 * @return the audiences  found
	 * @throws RestClientException if the request fails
	 */
	@Tool(description = "search Audiences filtered by query")
	public String searchAudiences(String query) {
		String token = getAccessToken(); // Get the access token from the environment or generate it
		//System.out.println(token);
		UriBuilder uriBuilder = UriComponentsBuilder.fromUriString(BASE_URL + "v2/audiences/");
		uriBuilder.queryParam("filter.audience.types", query);
		// set number of results by default to 1
		uriBuilder.queryParam("take",1);
		String uri = uriBuilder.build().toString();
		System.out.println("Request URI: " + uri);
		ResponseEntity<AudiencesSearchResponse> tagsSearchResponse = restClient.get()
  		.uri(uri)
		.header("x-api-key", token)
		.header("Cache-Control", "no-cache")
		.header("Authorization", "Bearer "+ token)
		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	  	.header("Accept", "application/json")
		.header("Connection", "keep-alive")
		.header("Accept-Encoding", "gzip, deflate, br")
		.header("Accept", "*/*")

  		.retrieve()
  		.toEntity(AudiencesSearchResponse.class);
		
		String data = String.format("Type: %s, Parents: %s Id: %s Name:%s Disambiguation: %s Tags: %s",
				tagsSearchResponse.getBody().getAudiencesSearchResult().getAudiences().get(0).getType(),
				printAudienceParents(tagsSearchResponse.getBody().getAudiencesSearchResult().getAudiences().get(0).getParents()),
				tagsSearchResponse.getBody().getAudiencesSearchResult().getAudiences().get(0).getId(),
				tagsSearchResponse.getBody().getAudiencesSearchResult().getAudiences().get(0).getName(),
				tagsSearchResponse.getBody().getAudiencesSearchResult().getAudiences().get(0).getDisambiguation(),
				printAudienceTags(tagsSearchResponse.getBody().getAudiencesSearchResult().getAudiences().get(0).getTags())
				);
		return data;
	}


	private String printAudienceTags(ArrayList<AudienceTag> tags) {
		StringBuilder sb = new StringBuilder();
		if (tags != null && !tags.isEmpty()) {
			for (AudienceTag tag : tags) {
				sb.append("Id: ").append(tag.getId())
				  .append(", Name: ").append(tag.getName())
				  .append(", Type: ").append(tag.getType()).append("; ");
			}
			// Remove the last semicolon and space
			if (sb.length() > 0) {
				sb.setLength(sb.length() - 2);
			}
		} else {
			sb.append("No tags available");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		InsightsService client = new InsightsService();
		/*
		HashMap<String, String> signals = new HashMap<>();
		signals.put("type", "entities"); 
		signals.put("value", "FCE8B172-4795-43E4-B222-3B550DC05FD9");
		System.out.println(client.recommendEntities("place", "40.7831° N, 73.9712° W", null, signals));
		*/
		client.BASE_URL="https://hackathon.api.qloo.com/";
		client.INSIGHTS_API_TOKEN="YOUR_QLOO_API_KEY";
		//System.out.println(client.getTagsTypes());
		//System.out.println(client.searchTags("vegan"));
		//System.out.println(client.getAudiencesTypes());
		System.out.println(client.searchAudiences("urn:audience:communities"));
	}

}
