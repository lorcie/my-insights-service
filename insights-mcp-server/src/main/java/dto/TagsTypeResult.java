package dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
example of TagsType data

    {
        "parents": [
            {
                "type": "artist"
            }
        ],
        "type": "urn:tag:artist:qloo"
    },

* 
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class TagsTypeResult {
    @JsonProperty("type") private String type;
    @JsonProperty("parents") private ArrayList<TagsType> parents;

    // Default constructor
    public TagsTypeResult() {
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
    public void setProperties(ArrayList<TagsType> parents) {
        this.parents = parents;
    }
}
