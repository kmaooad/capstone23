package edu.kmaooad.capstone23.competences.commands;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

import java.util.List;

public class UpdateProj {

    @NotNull
    private ObjectId id;

    @NotBlank
    @Size(min = 3, max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$")
    private String name;

    private String description;

    private List<ObjectId> skills;

    private List<ObjectId> skillSets;

    public String getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

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

    public List<ObjectId> getSkills() {
        return skills;
    }

    public void setSkills(List<ObjectId> skills) {
        this.skills = skills;
    }

    public List<ObjectId> getSkillSets() {
        return skillSets;
    }

    public void setSkillSets(List<ObjectId> skillSets) {
        this.skillSets = skillSets;
    }
}
