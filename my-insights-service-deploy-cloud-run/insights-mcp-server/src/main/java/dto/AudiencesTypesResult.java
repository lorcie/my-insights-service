package dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AudiencesTypesResult {
    @JsonProperty("audience_types") private ArrayList<AudiencesTypeResult> audiencesTypes;

    // Default constructor
    public AudiencesTypesResult() {
    }

    // Getters and Setters
    public ArrayList<AudiencesTypeResult> getAudiencesTypes() {
        return audiencesTypes;
    }
    public void setAudiencesTypes(ArrayList<AudiencesTypeResult> audiencesTypes) {
         this.audiencesTypes = audiencesTypes;
    }
   
}
