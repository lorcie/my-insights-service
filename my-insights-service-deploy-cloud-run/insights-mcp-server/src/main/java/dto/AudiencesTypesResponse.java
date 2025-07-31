package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AudiencesTypesResponse {
    @JsonProperty("success") private Boolean success;
    @JsonProperty("results") private AudiencesTypesResult audiencesTypesResult;

    // Default constructor
    public AudiencesTypesResponse() {
    }

    // Getters and Setters
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public AudiencesTypesResult getAudiencesTypesResult() {
        return audiencesTypesResult;
    }
    public void setAudiencesTypesResult(AudiencesTypesResult audiencesTypesResult) {
        this.audiencesTypesResult = audiencesTypesResult;
    }
}
