package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.AddSkillToSkillSet;
import edu.kmaooad.capstone23.competences.events.SkillToSkillSetAdded;
import edu.kmaooad.capstone23.competences.services.SkillService;
import edu.kmaooad.capstone23.competences.services.SkillSetService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;


@RequestScoped
public class AddSkillToSkillSetHandler implements CommandHandler<AddSkillToSkillSet, SkillToSkillSetAdded> {

    @Inject
    private SkillSetService service;

    @Inject
    private SkillService skillService;

    @Override
    public Result<SkillToSkillSetAdded> handle(AddSkillToSkillSet command) {

        var skill = skillService.findById(command.getSkillId().toString());
        var skillSet = service.findById(command.getSkillSetId().toString());

        if (skill.isEmpty())
            return new Result<>(ErrorCode.EXCEPTION, "Has not existing skill");

        if (skillSet.isEmpty())
            return new Result<>(ErrorCode.EXCEPTION, "Has not existing skillSet");

        var skillIds = skillSet.get().skillIds;

        if (skillIds != null && skillIds.contains(command.getSkillId())) {
            return new Result<>(ErrorCode.EXCEPTION, "Already contains skill");
        }

        var skillSetValue = skillSet.get();
        if (skillIds == null) {
            skillSetValue.skillIds = new ArrayList<>();
        }
        skillSetValue.skillIds.add(command.getSkillId());
        service.update(skillSetValue);

        SkillToSkillSetAdded result = new SkillToSkillSetAdded(skillSetValue);

        return new Result<SkillToSkillSetAdded>(result);
    }
}