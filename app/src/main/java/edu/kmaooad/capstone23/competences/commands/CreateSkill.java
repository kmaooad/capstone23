package edu.kmaooad.capstone23.competences.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

public class CreateSkill {

    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String skillName;


    private ObjectId parentSkill;

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public ObjectId getParentSkill() {
        return parentSkill;
    }

    public void setParentSkill(ObjectId parentSkill) {
        this.parentSkill = parentSkill;
    }
}