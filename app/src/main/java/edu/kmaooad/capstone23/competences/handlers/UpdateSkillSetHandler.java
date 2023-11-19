package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.UpdateSkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import edu.kmaooad.capstone23.competences.events.SkillSetUpdated;
import edu.kmaooad.capstone23.competences.services.SkillSetService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;


@RequestScoped
public class UpdateSkillSetHandler implements CommandHandler<UpdateSkillSet, SkillSetUpdated> {
    @Inject
    SkillSetService service;

    public Result<SkillSetUpdated> handle(UpdateSkillSet command) {
        var id = command.getSkillSetId();
        Optional<SkillSet> skillSet = service.findById(id);

        if (skillSet.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, String.format("Skill set with id: %s does not exist", id));

        SkillSet skillSetItem = skillSet.get();
        skillSetItem.name = command.getSkillSetName();

        service.update(skillSetItem);

        return new Result<>(new SkillSetUpdated(skillSetItem.id.toString(), skillSetItem.name));
    }
}