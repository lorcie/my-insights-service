package dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagsSearchResult {
    @JsonProperty("tags") private ArrayList<TagResult> tags;

    // Default constructor
    public TagsSearchResult() {
    }

    // Getters and Setters
    public ArrayList<TagResult> getTags() {
        return tags;
    }
    public void setTags(ArrayList<TagResult> tags) {
        this.tags = tags;
    }
}

