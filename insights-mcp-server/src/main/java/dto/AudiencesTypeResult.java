package dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AudiencesTypeResult {
    @JsonProperty("type") private String type;
    @JsonProperty("parents") private ArrayList<AudiencesType> parents;

    // Default constructor
    public AudiencesTypeResult() {
    }

    // Getters and Setters
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public ArrayList<AudiencesType> getParents() {
        return parents;
    }
    public void setProperties(ArrayList<AudiencesType> parents) {
        this.parents = parents;
    }
}
