package edu.kmaooad.capstone23.orgs.handlers;

import java.util.stream.Collectors;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.commands.ImportOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.events.OrgImport;
import edu.kmaooad.capstone23.orgs.services.OrgsServiceImpl;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class ImportOrgHandler implements CommandHandler<ImportOrg, OrgImport> {

    @Inject
    private OrgsServiceImpl service;

    public Result<OrgImport> handle(ImportOrg command) {
        var orgs = command.getOrgs().stream().map(org -> this.mapCommandToEntity(org)).collect(Collectors.toList());

        var orgIds = new OrgImport(this.service.bulkInsert(orgs)
                                              .stream()
                                              .map(org -> org.id.toString())
                                              .collect(Collectors.toList())
                                  );

        return new Result<OrgImport>(orgIds);
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
