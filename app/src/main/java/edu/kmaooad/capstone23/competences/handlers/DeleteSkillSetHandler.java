package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.DeleteSkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import edu.kmaooad.capstone23.competences.events.SkillSetDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class DeleteSkillSetHandler implements CommandHandler<DeleteSkillSet, SkillSetDeleted> {


    @Inject
    private SkillSetRepository repository;


    @Override
    public Result<SkillSetDeleted> handle(DeleteSkillSet command) {
        ObjectId id = command.getId();
        SkillSet skillSet = repository.findById(id);

        if (skillSet == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Skill set not found");
        }

        repository.delete(skillSet);

        return new Result<>(new SkillSetDeleted(skillSet));
    }
}
