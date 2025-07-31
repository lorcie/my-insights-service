package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagsSearchResponse {
    @JsonProperty("success") private Boolean success;
    @JsonProperty("results") private TagsSearchResult tagsSearchResult;

    // Default constructor
    public TagsSearchResponse() {
    }

    // Getters and Setters
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public TagsSearchResult getTagsSearchResult() {
        return tagsSearchResult;
    }
    public void setTagsSearchResult(TagsSearchResult tagsSearchResult) {
        this.tagsSearchResult = tagsSearchResult;
    }
}
