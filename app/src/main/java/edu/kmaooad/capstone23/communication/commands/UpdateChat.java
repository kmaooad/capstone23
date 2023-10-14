package edu.kmaooad.capstone23.communication.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateChat {
    @NotBlank
    private String id;

    @Size(min = 3, max = 100)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    @Size(max = 150)
    private String description;

    @NotBlank
    @Pattern(regexp = "Private|Public")
    private String accessType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

