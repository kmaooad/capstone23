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
    private String skillSetId;

    public void setSkillSetName(String skillSetName) {
        this.skillSetName = skillSetName;
    }

    public void setSkillSetId(String skillSetId) {
        this.skillSetId = skillSetId;
    }

    public String getSkillSetName() {
        return skillSetName;
    }

    public String getSkillSetId() {
        return skillSetId;
    }
}
