package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.common.ValidatingHandler;
import edu.kmaooad.capstone23.orgs.commands.RejectOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.OrgRejected;
import edu.kmaooad.capstone23.orgs.services.MailService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class RejectOrgHandler implements CommandHandler<RejectOrg, OrgRejected> {

    @Inject
    private OrgsRepository orgsRepository;

    @Inject
    private MailService mailService;

    @Override
    public Result<OrgRejected> handle(RejectOrg command) {
        Org org = orgsRepository.findById(new ObjectId(command.id));

        if (org == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Org with this ID does not exist");
        }
        if (!org.isActive) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Org already rejected");
        }

        org.isActive = false;
        orgsRepository.insert(org);
        mailService.sendEmail(command.reason, org.email);

        return new Result<>(new OrgRejected(org.id.toString()));
    }
}