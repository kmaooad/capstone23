package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.UpdateSkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import edu.kmaooad.capstone23.competences.events.SkillSetUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;


@RequestScoped
public class UpdateSkillSetHandler implements CommandHandler<UpdateSkillSet, SkillSetUpdated> {
    @Inject
    SkillSetRepository repository;

    public Result<SkillSetUpdated> handle(UpdateSkillSet command) {
        var id = command.getId();
        Optional<SkillSet> skillSet = repository.findById(id);

        if (skillSet.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, String.format("Skill set with id: %s does not exist", id));

        SkillSet skillSetItem = skillSet.get();
        skillSetItem.name = command.getSkillSetName();

        repository.update(skillSetItem);

        return new Result<>(new SkillSetUpdated(skillSetItem.id.toString(), skillSetItem.name));
    }
}