package edu.kmaooad.capstone23.competences.events;

import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.notifications.Notify;
import edu.kmaooad.capstone23.notifications.impls.EmailNotificationChannel;

import java.util.Optional;

@Notify(EmailNotificationChannel.class)
public class SkillDeleted {
    private Skill skill;

    public SkillDeleted(Optional<Skill> skill) {
        this.skill = skill.get();
    }

    public Skill getSkill() {
        return skill;
    }
}
