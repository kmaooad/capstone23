package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.RemoveSkillFromSkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import edu.kmaooad.capstone23.competences.events.SkillFromSkillSetRemoved;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;



@RequestScoped
public class RemoveSkillFromSkillSetHandler implements CommandHandler<RemoveSkillFromSkillSet, SkillFromSkillSetRemoved> {

    @Inject
    private SkillSetRepository skillSetRepository;

    @Inject
    private SkillsRepository skillsRepository;


    @Override
    public Result<SkillFromSkillSetRemoved> handle(RemoveSkillFromSkillSet command) {

        var skill = skillsRepository.findById(command.getSkillId().toString());
        var skillSet = skillSetRepository.findById(command.getSkillSetId().toString());

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
        skillSetRepository.update(skillSetValue);

        SkillFromSkillSetRemoved result = new SkillFromSkillSetRemoved(skillSetValue);

        return new Result<SkillFromSkillSetRemoved>(result);
    }
}