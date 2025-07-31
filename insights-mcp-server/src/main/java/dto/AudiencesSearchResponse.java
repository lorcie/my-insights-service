package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AudiencesSearchResponse {
    @JsonProperty("success") private Boolean success;
    @JsonProperty("results") private AudiencesSearchResult audiencesSearchResult;

    // Default constructor
    public AudiencesSearchResponse() {
    }

    // Getters and Setters
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public AudiencesSearchResult getAudiencesSearchResult() {
        return audiencesSearchResult;
    }
    public void setAudiencesSearchResult(AudiencesSearchResult audiencesSearchResult) {
        this.audiencesSearchResult = audiencesSearchResult;
    }
}

