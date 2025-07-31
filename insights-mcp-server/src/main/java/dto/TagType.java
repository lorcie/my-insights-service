package dto;

/*
 * Example of a TagType data
 * 
 * {
                        "name": "Canonical",
                        "tag_id": "urn:tag:collection:canonical",
                        "type": "urn:tag:collection",
                        "value": "urn:tag:collection:canonical"
    }
 */
public class TagType {
    private String name;
    private String tagId;
    private String type;
    private String value;

    // Default constructor
    public TagType() {
    }
    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTagId() {
        return tagId;
    }
    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

}
