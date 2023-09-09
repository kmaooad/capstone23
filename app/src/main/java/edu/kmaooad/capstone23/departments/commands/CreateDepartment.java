package edu.kmaooad.capstone23.departments.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateDepartment {

    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String orgName) {
        this.name = orgName;
    }


    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String orgDescription) {
        this.description = orgDescription;
    }

    @NotBlank
    private String parent;

    public String getParent() {
        return parent;
    }

    public void setParent(String orgParent) {
        this.parent = orgParent;
    }

}