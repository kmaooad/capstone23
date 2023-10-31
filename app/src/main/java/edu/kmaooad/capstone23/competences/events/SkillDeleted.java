package edu.kmaooad.capstone23.competences.events;

import edu.kmaooad.capstone23.competences.dal.Skill;

import java.util.Optional;

public class SkillDeleted {
    private Skill skill;

    public SkillDeleted(Skill skill) {
        this.skill = skill;
    }

    public Skill getSkill() {
        return skill;
    }
}
