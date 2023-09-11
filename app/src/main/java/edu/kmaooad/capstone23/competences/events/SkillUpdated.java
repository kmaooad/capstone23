package edu.kmaooad.capstone23.competences.events;

import edu.kmaooad.capstone23.competences.dal.Skill;

public class SkillUpdated {


    private Skill skill;

    public SkillUpdated(Skill skill) {
        this.skill = skill;
    }

    public Skill getSkill() {
        return skill;
    }
}
