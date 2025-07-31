package dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/* properties example: 
{
                    "release_year": 2024,
                    "release_date": "2024-02-02",
                    "description": "A reclusive author who writes espionage novels about a secret agent and a global spy syndicate realizes that the plot of the new book she's writing starts to mirror real-world events in real time.",
                    "content_rating": "PG-13",
                    "duration": 139,
                    "image": {
                        "url": "https://images.qloo.com/i/5081E807-5182-4A88-9559-A07A8A0DD19B-420x-auto.jpg"
                    },
                    "akas": [
                        {
                            "value": "أرغيل",
                            	    "languages": [
                                "ar"
                            ]
                        },
                   ],
                    "filming_location": "Santorini, Greece",
                    "production_companies": [
                        "Marv Films",
                        "Apple Original Films",
                        "Cloudy Productions"
                    ],
                    "release_country": [
                        "United States",
                        "United Kingdom"
                    ],
                    "short_descriptions": [
                        {
                            "value": "film uit 2024 van Matthew Vaughn",
                            "languages": [
                                "nl"
                            ]
                        },
                   ],
                    "websites": [
                        "https://www.facebook.com/argyllemovie/about",
                        "https://www.instagram.com/argyllemovie/"
                    ]
}
*/

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityProperties {
    @JsonProperty("address") private String address;
    @JsonProperty("phone") private String phone;
    @JsonProperty("business_rating") private String businessRating;
    @JsonProperty("menu_url") private String menuUrl;
    @JsonProperty("release_year") private Integer releaseYear;
    @JsonProperty("release_date") private String releaseDate;
    @JsonProperty("description") private String description;
    @JsonProperty("content_rating") private String contentRating;
    @JsonProperty("duration") private Integer duration;
    @JsonProperty("image") private EntityImage image;
    @JsonProperty("akas") private ArrayList<EntityAka> akas;
    @JsonProperty("filming_location") private String filmingLocation;
    @JsonProperty("production_companies") private ArrayList<String> productionCompanies;
    @JsonProperty("release_country") private ArrayList<String> releaseCountry;
    @JsonProperty("short_descriptions") private ArrayList<EntityShortDescription> shortDescriptions;
    @JsonProperty("websites") private ArrayList<String> websites;

    // Default constructor
    public EntityProperties() {
    }

    // Getters and Setters
    public String getBusinessRating() {
        return businessRating;
    }
    public void setBusinessRating(String businessRating) {
        this.businessRating = businessRating;
    }
    public String getMenuUrl() {
        return menuUrl;
    }
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getReleaseYear() {
        return releaseYear;
    }
    
    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public EntityImage getImage() {
        return image;
    }

    public void setImage(EntityImage image) {
        this.image = image;
    }

    public ArrayList<EntityAka> getAkas() {
        return akas;
    }

    public void setAkas(ArrayList<EntityAka> akas) {
        this.akas = akas;
    }

    public String getFilmingLocation() {
        return filmingLocation;
    }

    public void setFilmingLocation(String filmingLocation) {
        this.filmingLocation = filmingLocation;
    }

    public ArrayList<String> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(ArrayList<String> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }
    public ArrayList<String> getReleaseCountry() {
        return releaseCountry;
    }
    public void setReleaseCountry(ArrayList<String> releaseCountry) {
        this.releaseCountry = releaseCountry;
    }
    public ArrayList<EntityShortDescription> getShortDescriptions() {
        return shortDescriptions;
    }
    public void setShortDescriptions(ArrayList<EntityShortDescription> shortDescriptions) {
        this.shortDescriptions = shortDescriptions;
    }
    public ArrayList<String> getWebsites() {
        return websites;
    }
    public void setWebsites(ArrayList<String> websites) {
        this.websites = websites;
    }

}
