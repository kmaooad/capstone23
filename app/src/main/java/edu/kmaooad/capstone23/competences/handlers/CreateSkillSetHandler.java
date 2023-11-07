package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.dal.MongoSkillsRepository;
import edu.kmaooad.capstone23.competences.services.SkillSetService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import edu.kmaooad.capstone23.competences.commands.CreateSkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.events.SkillSetCreated;
import org.bson.types.ObjectId;

import java.util.Optional;

@RequestScoped
public class CreateSkillSetHandler implements CommandHandler<CreateSkillSet, SkillSetCreated> {

    @Inject
    private SkillSetService service;

    @Inject
    private MongoSkillsRepository skillsRepository;
    public Result<SkillSetCreated> handle(CreateSkillSet command) {

        SkillSet skillSet = new SkillSet();
        skillSet.name = command.getSkillSetName();
        skillSet.skillIds = command.getSkillIds();

        if(skillSet.skillIds != null) {
            for ( ObjectId item : skillSet.skillIds) {
                if(skillsRepository.findById(item.toString()).equals(Optional.empty()))
                    return new Result<>(ErrorCode.EXCEPTION, "Has not existing skill");
            }
        }

        service.insert(skillSet);

        SkillSetCreated result = new SkillSetCreated(skillSet.id.toString());

        return new Result<SkillSetCreated>(result);
    }
}