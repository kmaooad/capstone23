package edu.kmaooad.capstone23.orgs;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateOrgHandler implements CommandHandler<CreateOrg, OrgCreated> {

    @Inject
    private OrgsRepository repository;

    public Result<OrgCreated> handle(CreateOrg command) {

        Org org = new Org();
        org.name = command.getOrgName();

        repository.insert(org);

        OrgCreated result = new OrgCreated(org.id.toString());

        return new Result<OrgCreated>(result);
    }
}