package edu.kmaooad.capstone23.competences.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

import java.util.List;

public class CreateSkillSet {

    @NotBlank
    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String skillSetName;
    private List<ObjectId> skillIds;

    public List<ObjectId> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<ObjectId> skillIds) {
        this.skillIds = skillIds;
    }


    public void setSkillSetName(String skillSetName) {
        this.skillSetName = skillSetName;
    }

    public String getSkillSetName() {
        return skillSetName;
    }
}