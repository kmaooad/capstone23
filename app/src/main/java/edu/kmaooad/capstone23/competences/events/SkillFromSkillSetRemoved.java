package edu.kmaooad.capstone23.competences.events;

import edu.kmaooad.capstone23.competences.dal.SkillSet;

public class SkillFromSkillSetRemoved {

    private SkillSet skillSet;

    public SkillFromSkillSetRemoved(SkillSet skillSet) {
        this.skillSet = skillSet;
    }

    public SkillSet getSkillSet() {
        return skillSet;
    }

}
