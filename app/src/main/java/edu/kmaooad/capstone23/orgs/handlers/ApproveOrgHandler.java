package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.ApproveOrgSub;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.OrgApproved;
import edu.kmaooad.capstone23.orgs.services.MailService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class ApproveOrgHandler implements CommandHandler<ApproveOrgSub, OrgApproved> {

    @Inject
    private OrgsRepository orgsRepository;

    private final String defaultEmailText = "Your organizations`s submission has been approved";
    private MailService mailService = new MailService();

    public Result<OrgApproved> handle(ApproveOrgSub command) {
        Org org = orgsRepository.findById(new ObjectId(command.getOrgId()));
        if (org == null) {
            return new Result(ErrorCode.EXCEPTION, "Org not found!");
        }

        if (org.isActive){
            return new Result(ErrorCode.EXCEPTION, "Org already approved!");
        } else {
            org.isActive = true;
        }

        mailService.sendEmail(defaultEmailText, org.email);
        OrgApproved result = new OrgApproved(org.id.toString());

        return new Result<OrgApproved>(result);
    }
}