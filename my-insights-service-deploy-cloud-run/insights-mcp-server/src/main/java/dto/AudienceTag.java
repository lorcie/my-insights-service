package dto;

/*
 * example of AudienceTag data
                    {
                        "id": "urn:tag:collection:canonical",
                        "name": "Canonical",
                        "type": "urn:tag:collection"
                    }
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AudienceTag {
    @JsonProperty("id") private String id;
    @JsonProperty("name") private String name;
    @JsonProperty("type") private String type;

    // Default constructor
    public AudienceTag() {
    }
    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}
