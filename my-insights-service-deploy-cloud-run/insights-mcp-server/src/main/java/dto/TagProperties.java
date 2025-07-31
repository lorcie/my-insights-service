package dto;

/*
 * example of TagProperties data
 * {
        "description": "Vegan cuisine entails food that is devoid of all products derived wholly or partially from animals. The focus is on plant-based foods, including fruits, vegetables, grains, nuts, and seeds. Vegan dishes can be diverse and flavorful, often leveraging spices, herbs, and varying cooking techniques to create satisfying dishes.",
        "history": "The concept of veganism was first formulated in the 1944 by Donald Watson, co-founder of the Vegan Society in the UK, to mean a diet free of animal-based foods (such as meat, dairy products, eggs, and honey). The popularity of veganism has increased significantly in the 21st century.",
        "origin": "England",
        "presentation_style": "Varies"
    }
 * 
 */
public class TagProperties {
    private String description;
    private String history;
    private String origin;
    private String presentationStyle;

    // Default constructor
    public TagProperties() {
    }

    // Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPresentationStyle() {
        return presentationStyle;
    }

    public void setPresentationStyle(String presentationStyle) {
        this.presentationStyle = presentationStyle;
    }   

}
