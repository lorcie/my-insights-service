package dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntitiesResult {
    @JsonProperty("entities") private ArrayList<EntityResult> entities;

    // Default constructor
    public EntitiesResult() {
    }

    // Getters and Setters
    public ArrayList<EntityResult> getEntities() {
        return entities;
    }
    public void setEntities(ArrayList<EntityResult> entities) {
        this.entities = entities;
    }
}
