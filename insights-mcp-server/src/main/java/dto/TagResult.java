package dto;

/*
Example of TagResult

            {
                "parents": [
                    {
                        "type": "urn:entity:place"
                    }
                ],
                "type": "urn:tag:genre:place",
                "id": "urn:tag:genre:place:restaurant:vegan",
                "name": "Vegan",
                "popularity": 0.999286,
                "properties": {
                    "description": "Vegan cuisine entails food that is devoid of all products derived wholly or partially from animals. The focus is on plant-based foods, including fruits, vegetables, grains, nuts, and seeds. Vegan dishes can be diverse and flavorful, often leveraging spices, herbs, and varying cooking techniques to create satisfying dishes.",
                    "history": "The concept of veganism was first formulated in the 1944 by Donald Watson, co-founder of the Vegan Society in the UK, to mean a diet free of animal-based foods (such as meat, dairy products, eggs, and honey). The popularity of veganism has increased significantly in the 21st century.",
                    "origin": "England",
                    "presentation_style": "Varies"
                },
                "tags": [
                    {
                        "name": "Canonical",
                        "tag_id": "urn:tag:collection:canonical",
                        "type": "urn:tag:collection",
                        "value": "urn:tag:collection:canonical"
                    }
                ]
            }

* 
 */

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagResult {
    @JsonProperty("type") private String type;
    @JsonProperty("parents") private ArrayList<TagsType> parents;
    @JsonProperty("id") private String id;
    @JsonProperty("name") private String name;
    @JsonProperty("popularity") private Double popularity;
    @JsonProperty("properties") private TagProperties properties;
    @JsonProperty("tags") private ArrayList<TagType> tags;

    // Default constructor
    public TagResult() {
    }

    // Getters and Setters
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public ArrayList<TagsType> getParents() {
        return parents;
    }
    public void setParents(ArrayList<TagsType> parents) {
        this.parents = parents;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPopularity() {
        return popularity;
    }
    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }
    public TagProperties getProperties() {
        return properties;
    }
    public void setProperties(TagProperties properties) {
        this.properties = properties;
    }
    public ArrayList<TagType> getTags() {
        return tags;
    }
    public void setTags(ArrayList<TagType> tags) {
        this.tags = tags;
    }

}
