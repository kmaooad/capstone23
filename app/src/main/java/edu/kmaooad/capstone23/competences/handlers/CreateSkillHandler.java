package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateSkill;
import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.MongoSkillsRepository;
import edu.kmaooad.capstone23.competences.events.SkillCreated;
import edu.kmaooad.capstone23.competences.services.SkillService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateSkillHandler implements CommandHandler<CreateSkill, SkillCreated> {


    @Inject
    SkillsRepository repository;



    @Override
    public Result<SkillCreated> handle(CreateSkill command) {
        var t = new Skill();
        t.name = command.getSkillName();
        t.setId(command.getParentSkill());
        try {
            var result = skillService.insert(t);
            return new Result<>(new SkillCreated(result.id));
        } catch (IllegalArgumentException e) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, e.getMessage());
        }
    }
}
