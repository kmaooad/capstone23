package edu.kmaooad.capstone23.competences.events;

import org.bson.types.ObjectId;

public class SkillCreated {

    private ObjectId skill;

    public SkillCreated(ObjectId skill) {
        this.skill = skill;
    }

    public ObjectId getSkill() {
        return skill;
    }
}
