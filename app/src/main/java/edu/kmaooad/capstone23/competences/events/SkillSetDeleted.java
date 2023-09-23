package edu.kmaooad.capstone23.competences.events;

import edu.kmaooad.capstone23.competences.dal.SkillSet;

public class SkillSetDeleted {
    private SkillSet skillSet;

    public SkillSetDeleted(SkillSet skillSet) {
        this.skillSet = skillSet;
    }

    public SkillSet getSkill() {
        return skillSet;
    }
}
