package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateSkillSetHandler implements CommandHandler<CreateSkillSet, SkillSetCreated> {

    @Inject
    private SkillSetRepository repository;

    public Result<SkillSetCreated> handle(CreateSkillSet command) {

        SkillSet skillSet = new SkillSet();
        skillSet.name = command.getSkillSetName();

        repository.insert(skillSet);

        SkillSetCreated result = new OrgCreated(skillSet.id.toString());

        return new Result<SkillSetCreated>(result);
    }
}