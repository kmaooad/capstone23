package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.ban.commands.IsEntityBannedV2;
import edu.kmaooad.capstone23.ban.service.EntityBanService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.ApproveOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.events.OrgApproved;
import edu.kmaooad.capstone23.mail.service.MailService;
import edu.kmaooad.capstone23.orgs.services.OrgService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class ApproveOrgHandler implements CommandHandler<ApproveOrg, OrgApproved> {

    @Inject
    private OrgService orgService;

    @Inject
    private MailService mailService;

    @Inject
    private EntityBanService banService;

    private static final String defaultEmailText = "Your organizations`s submission has been approved";

    public Result<OrgApproved> handle(ApproveOrg command) {
        final Optional<Org> valid_org = orgService.getOrgByIdOptional(command.getOrgId());
        if (valid_org.isEmpty()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Org not found!");
        }
        if (banService.findForEntity(IsEntityBannedV2.ORGANIZATION_BAN_ENTITY_TYPE, valid_org.get().id.toString()).isPresent()) {
            return new Result<>(ErrorCode.EXCEPTION, "Org is banned");
        }
        final Org org = valid_org.get();

        if (org.isActive) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Org already approved!");
        } else {
            org.isActive = true;
        }
        orgService.updateOrg(org);

        mailService.sendEmail(defaultEmailText, command.getOrgEmail());
        OrgApproved result = new OrgApproved(org.id.toString());

        return new Result<>(result);
    }
}