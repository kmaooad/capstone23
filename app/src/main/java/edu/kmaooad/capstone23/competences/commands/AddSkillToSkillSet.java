package edu.kmaooad.capstone23.competences.commands;

import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

public class AddSkillToSkillSet {
    @NotNull
    private ObjectId skillId;

    @NotNull
    private ObjectId skillSetId;

    public ObjectId getSkillSetId() {
        return skillSetId;
    }

    public void setSkillSetId(ObjectId skillSetId) {
        this.skillSetId = skillSetId;
    }

    public ObjectId getId() {
        return skillId;
    }

    public void setId(ObjectId id) {
        this.skillId = id;
    }
}
