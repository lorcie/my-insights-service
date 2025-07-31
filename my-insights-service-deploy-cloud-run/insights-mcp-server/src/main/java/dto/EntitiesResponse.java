package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntitiesResponse {
    @JsonProperty("success") private Boolean success;
    @JsonProperty("results") private EntitiesResult entitiesResult;

    // Default constructor
    public EntitiesResponse() {
    }

    // Getters and Setters
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public EntitiesResult getEntitiesResult() {
        return entitiesResult;
    }
    public void setEntitiesResult(EntitiesResult entitiesResult) {
        this.entitiesResult = entitiesResult;
    }
}
