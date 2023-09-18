package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.ApproveOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.OrgApproved;
import edu.kmaooad.capstone23.orgs.services.MailService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@RequestScoped
public class ApproveOrgHandler implements CommandHandler<ApproveOrg, OrgApproved> {

    @Inject
    private OrgsRepository orgsRepository;

    @Inject
    private MailService mailService;

    private static final String defaultEmailText = "Your organizations`s submission has been approved";

    public Result<OrgApproved> handle(ApproveOrg command) {
        final Optional<Org> valid_org = orgsRepository.findById(command.getOrgId());
        if (valid_org.isEmpty()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Org not found!");
        }
        final Org org = valid_org.get();

        if (org.isActive){
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Org already approved!");
        } else {
            org.isActive = true;
        }
        orgsRepository.update(org);

        mailService.sendEmail(defaultEmailText, org.email);
        OrgApproved result = new OrgApproved(org.id.toString());

        return new Result<>(result);
    }
}