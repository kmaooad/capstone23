package edu.kmaooad.capstone23.competences.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateSkillSet {
    @NotBlank
    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String skillSetName;

    @NotNull
    private String id;

    public void setSkillSetName(String skillSetName) {
        this.skillSetName = skillSetName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkillSetName() {
        return skillSetName;
    }

    public String getId() {
        return id;
    }
}
