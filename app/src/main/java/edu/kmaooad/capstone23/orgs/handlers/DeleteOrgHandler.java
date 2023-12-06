package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.DeleteOrg;
import edu.kmaooad.capstone23.orgs.events.OrgDeleted;
import edu.kmaooad.capstone23.orgs.services.OrgsServiceImpl;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class DeleteOrgHandler implements CommandHandler<DeleteOrg, OrgDeleted> {

    @Inject
    OrgsServiceImpl orgsService;

    @Override
    public Result<OrgDeleted> handle(DeleteOrg command) {
        var org = orgsService.findById(command.getOrgId());
        if (org == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Organization not found");
        }
        orgsService.deleteById(org.id);
        return new Result<>(new OrgDeleted(true));
    }
}
