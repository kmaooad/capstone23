package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.AddSkillToSkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import edu.kmaooad.capstone23.competences.events.SkillToSkillSetAdded;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;


@RequestScoped
public class AddSkillToSkillSetHandler implements CommandHandler<AddSkillToSkillSet, SkillToSkillSetAdded> {

    @Inject
    private SkillSetRepository skillSetRepository;

    @Inject
    private SkillsRepository skillsRepository;



    @Override
    public Result<SkillToSkillSetAdded> handle(AddSkillToSkillSet command) {

        var skill = skillsRepository.findById(command.getSkillId().toString());
        var skillSet = skillSetRepository.findById(command.getSkillSetId().toString());

        if(skill.isEmpty())
            return new Result<>(ErrorCode.EXCEPTION, "Has not existing skill");

        if(skillSet.isEmpty())
            return new Result<>(ErrorCode.EXCEPTION, "Has not existing skillSet");

        if(skillSet.get().skillIds.contains(command.getSkillId())) {
            return new Result<>(ErrorCode.EXCEPTION, "Already contains skill");
        }

        var skillSetValue = skillSet.get();
        skillSetValue.skillIds.add(command.getSkillId());
        skillSetRepository.update(skillSetValue);

        SkillToSkillSetAdded result = new SkillToSkillSetAdded(skillSetValue);

        return new Result<SkillToSkillSetAdded>(result);
    }
}