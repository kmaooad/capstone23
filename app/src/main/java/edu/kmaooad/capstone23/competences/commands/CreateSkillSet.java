package edu.kmaooad.capstone23.competences.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateSkillSet {

    @NotBlank
    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String skillSetName;

    public void setSkillSetName(String skillSetName) {
        this.skillSetName = skillSetName;
    }

    public String getSkillSetName() {
        return skillSetName;
    }
}