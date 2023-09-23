package edu.kmaooad.capstone23.cvs.commands;

import org.bson.types.ObjectId;

public class RemoveSkillFromCV {
    private ObjectId cvId;
    private ObjectId skillId;

    public ObjectId getCvId() {
        return cvId;
    }

    public void setCvId(ObjectId cvId) {
        this.cvId = cvId;
    }

    public ObjectId getSkillId() {
        return skillId;
    }

    public void setSkillId(ObjectId skillId) {
        this.skillId = skillId;
    }
}
