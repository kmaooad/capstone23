package edu.kmaooad.capstone23.competences.events;

import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillSet;

public class SkillToSkillSetAdded {

    private SkillSet skillSet;

    public SkillToSkillSetAdded(SkillSet skillSet) {
        this.skillSet = skillSet;
    }

    public SkillSet getSkillSet() {
        return skillSet;
    }

}
