package edu.kmaooad.capstone23.competences.dal.dto;

import java.util.List;
import org.bson.types.ObjectId;

public class ProjectResponseDto {
    public ObjectId id;
    public String name;
    public String description;
    public List<ObjectId> skills;
    public List<ObjectId> skillSets;

    public ObjectId getId() {
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
