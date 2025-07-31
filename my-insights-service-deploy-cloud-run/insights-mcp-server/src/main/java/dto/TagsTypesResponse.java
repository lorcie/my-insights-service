package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagsTypesResponse {
    @JsonProperty("success") private Boolean success;
    @JsonProperty("results") private TagsTypesResult tagsTypesResult;

    // Default constructor
    public TagsTypesResponse() {
    }

    // Getters and Setters
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public TagsTypesResult getTagsTypesResult() {
        return tagsTypesResult;
    }
    public void setTagsTypesResult(TagsTypesResult tagsTypesResult) {
        this.tagsTypesResult = tagsTypesResult;
    }
}
