package dto;

/* entity short description example
 * 
 * {
        "value": "2024 film directed by Matthew Vaughn",
        "languages": [
            "en"
        ]
    }
 */
public class EntityShortDescription {
    private String value;
    private String[] languages;

    // Default constructor
    public EntityShortDescription() {
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
