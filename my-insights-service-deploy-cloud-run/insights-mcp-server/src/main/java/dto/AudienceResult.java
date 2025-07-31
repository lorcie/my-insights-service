package dto;

/*
 * example of udienceResult data
    {
                "entity_id": "22D0A7AF-7833-49F3-8E36-42E8D91EEC8B",
                "name": "Parents With Young Children",
                "parents": [
                    {
                        "id": "urn:audience:life_stage",
                        "name": "Life Stage",
                        "type": "urn:audience"
                    }
                ],
                "type": "urn:audience:life_stage",
                "id": "urn:audience:life_stage:parents_with_young_children",
                "disambiguation": "Parents With Young Children (Life Stage)",
                "tags": [
                    {
                        "id": "urn:tag:collection:canonical",
                        "name": "Canonical",
                        "type": "urn:tag:collection"
                    }
                ]
    }
 */

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AudienceResult {
    @JsonProperty("entity_id") private String entityId;
    @JsonProperty("name") private String name;
    @JsonProperty("parents") private ArrayList<AudiencesType> parents;
    @JsonProperty("type") private String type;
    @JsonProperty("id") private String id;
    @JsonProperty("disambiguation") private String disambiguation;
    @JsonProperty("tags") private ArrayList<AudienceTag> tags;

    // Default constructor
    public AudienceResult() {
    }

    // Getters and Setters
    public String getEntityId() {
        return entityId;
    }
    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<AudiencesType> getParents() {
        return parents;
    }
    public void setParents(ArrayList<AudiencesType> parents) {
        this.parents = parents;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDisambiguation() {
        return disambiguation;
    }
    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }
    public ArrayList<AudienceTag> getTags() {
        return tags;
    }
    public void setTags(ArrayList<AudienceTag> tags) {
        this.tags = tags;
    }

}
