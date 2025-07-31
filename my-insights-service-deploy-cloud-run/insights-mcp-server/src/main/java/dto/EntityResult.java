package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityResult {
    @JsonProperty("name") private String name;
    @JsonProperty("entity_id") private String entityId;
    @JsonProperty("type") private String type;
    @JsonProperty("subtype") private String subType;
    @JsonProperty("properties") private EntityProperties properties;

    // Default constructor
    public EntityResult() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEntityId() {
        return entityId;
    }
    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSubType() {
        return subType;
    }
    public void setSubType(String subType) {
        this.subType = subType;
    }
    public EntityProperties getProperties() {
        return properties;
    }
    public void setProperties(EntityProperties properties) {
        this.properties = properties;
    }
}
