package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.UserNotificationTrigger;
import edu.kmaooad.capstone23.notifications.services.NotifyService;
import edu.kmaooad.capstone23.orgs.commands.DeleteOrg;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.OrgDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class DeleteOrgHandler implements CommandHandler<DeleteOrg, OrgDeleted> {
    @Inject
    OrgsRepository repo;

    @Inject
    NotifyService notifyService;

    @Override
    public Result<OrgDeleted> handle(DeleteOrg command) {
        var org = repo.findById(command.getOrgId());
        if (org == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Organization not found");
        }
        repo.delete(org);

        notifyService.notify(UserNotificationTrigger.ORG_DELETED, "Deleted org: " + org.name);
        return new Result<>(new OrgDeleted(true));
    }
}
