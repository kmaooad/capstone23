package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.RemoveSkillFromSkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import edu.kmaooad.capstone23.competences.events.SkillFromSkillSetRemoved;
import edu.kmaooad.capstone23.competences.services.SkillService;
import edu.kmaooad.capstone23.competences.services.SkillSetService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;



@RequestScoped
public class RemoveSkillFromSkillSetHandler implements CommandHandler<RemoveSkillFromSkillSet, SkillFromSkillSetRemoved> {

    @Inject
    private SkillSetService service;

    @Inject
    private MongoSkillsRepository skillsRepository;


    @Override
    public Result<SkillFromSkillSetRemoved> handle(RemoveSkillFromSkillSet command) {

        var skill = skillService.findById(command.getSkillId());
        var skillSet = service.findById(command.getSkillSetId().toString());

        if (skill.isEmpty())
            return new Result<>(ErrorCode.EXCEPTION, "Has not existing skill in db");

        if (skillSet.isEmpty())
            return new Result<>(ErrorCode.EXCEPTION, "Has not existing skillSet in db");

        var skillIds = skillSet.get().skillIds;

        if (skillIds == null || !skillIds.contains(command.getSkillId())) {
            return new Result<>(ErrorCode.EXCEPTION, "Doesn`t contains skill");
        }

        var skillSetValue = skillSet.get();
        skillSetValue.skillIds.remove(command.getSkillId());
        service.update(skillSetValue);

        SkillFromSkillSetRemoved result = new SkillFromSkillSetRemoved(skillSetValue);

        return new Result<SkillFromSkillSetRemoved>(result);
    }
}