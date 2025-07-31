package dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AudiencesSearchResult {
    @JsonProperty("audiences") private ArrayList<AudienceResult> audiences;

    // Default constructor
    public AudiencesSearchResult() {
    }

    // Getters and Setters
    public ArrayList<AudienceResult> getAudiences() {
        return audiences;
    }
    public void setAudiences(ArrayList<AudienceResult> audiences) {
        this.audiences = audiences;
    }
}


