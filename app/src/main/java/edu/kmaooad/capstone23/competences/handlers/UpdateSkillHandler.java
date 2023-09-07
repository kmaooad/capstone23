package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.UpdateSkill;
import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import edu.kmaooad.capstone23.competences.events.SkillUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UpdateSkillHandler implements CommandHandler<UpdateSkill, SkillUpdated> {


    @Inject
    private SkillsRepository repository;


    @Override
    public Result<SkillUpdated> handle(UpdateSkill command) {
        var skill = new Skill();
        skill.id = command.getId();
        skill.name = command.getSkillName();
        skill.parentSkill = command.getParentSkill();
        try {
            var updatedSkill = repository.modify(skill);
            return new Result<>(new SkillUpdated(updatedSkill));
        } catch (IllegalArgumentException e) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, e.getMessage());
        }
    }
}
