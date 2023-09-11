package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import edu.kmaooad.capstone23.competences.commands.CreateSkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import edu.kmaooad.capstone23.competences.events.SkillSetCreated;

@RequestScoped
public class CreateSkillSetHandler implements CommandHandler<CreateSkillSet, SkillSetCreated> {

    @Inject
    private SkillSetRepository repository;

    public Result<SkillSetCreated> handle(CreateSkillSet command) {

        SkillSet skillSet = new SkillSet();
        skillSet.name = command.getSkillSetName();

        repository.insert(skillSet);

        SkillSetCreated result = new SkillSetCreated(skillSet.id.toString());

        return new Result<SkillSetCreated>(result);
    }
}