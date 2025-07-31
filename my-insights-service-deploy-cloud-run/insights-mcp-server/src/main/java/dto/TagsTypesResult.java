package dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagsTypesResult {
    @JsonProperty("tag_types") private ArrayList<TagsTypeResult> tagsTypes;

    // Default constructor
    public TagsTypesResult() {
    }

    // Getters and Setters
    public ArrayList<TagsTypeResult> getTagsTypes() {
        return tagsTypes;
    }
    public void setTagsTypes(ArrayList<TagsTypeResult> tagsTypes) {
        this.tagsTypes = tagsTypes;
    }
}
