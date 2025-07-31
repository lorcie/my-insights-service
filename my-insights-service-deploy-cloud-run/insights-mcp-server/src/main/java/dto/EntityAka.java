package dto;

/* entity aka example
 {
        "value": "Арґайл",
        "languages": [
            "uk"
        ]
     }
 */

public class EntityAka {
    private String value;
    private String[] languages;

    // Default constructor
    public EntityAka() {
    }

    // Getters and Setters
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }
}
