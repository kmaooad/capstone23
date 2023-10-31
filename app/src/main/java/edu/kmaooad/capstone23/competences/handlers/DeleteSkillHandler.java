package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.DeleteSkill;
import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import edu.kmaooad.capstone23.competences.events.SkillDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@RequestScoped
public class DeleteSkillHandler implements CommandHandler<DeleteSkill, SkillDeleted> {


    @Inject
    SkillsRepository repository;


    @Override
    public Result<SkillDeleted> handle(DeleteSkill command) {
        ObjectId id = command.getId();
        Optional<Skill> skill = repository.findById(String.valueOf(id));

        if (skill.isEmpty()) {
            return new Result<>(ErrorCode.EXCEPTION, "Skill not found");
        }

        // let's see if it has any children. We won't delete a skill that has children
        var allChildren = repository.findChildRepositories(id);
        if (allChildren.size() > 0) {
            return new Result<>(ErrorCode.EXCEPTION, "Skill has children");
        }

        repository.deleteSkill(skill.get());

        return new Result<>(new SkillDeleted(skill));
    }
}
