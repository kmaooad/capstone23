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
public class CreateOrgHandler implements CommandHandler<CreateOrg, OrgCreated> {

    @Inject
    private OrgsRepository repository;

    public Result<OrgCreated> handle(CreateOrg command) {
        Org org = this.mapCommandToEntity(command);

        this.repository.insert(org);

        OrgCreated result = new OrgCreated(org.id.toString());

        return new Result<OrgCreated>(result);
    }

    private Org mapCommandToEntity(CreateOrg command) {
        Org org = new Org();
        org.name = command.getOrgName();
        org.description = command.description;
        org.industry = command.industry;
        org.website = command.website;

        return org;
    }
}