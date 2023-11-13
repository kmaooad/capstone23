package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.RejectOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.OrgRejected;
import edu.kmaooad.capstone23.orgs.services.OrgsServiceImpl;
import edu.kmaooad.capstone23.mail.service.MailService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class RejectOrgHandler implements CommandHandler<RejectOrg, OrgRejected> {

    @Inject
    private OrgsServiceImpl orgService;

    @Inject
    private MailService mailService;

    @Override
    public Result<OrgRejected> handle(RejectOrg command) {
        final Optional<Org> optionalOrg = orgService.findByIdOptional(command.id);
        if (optionalOrg.isEmpty()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Org with this ID does not exist");
        }
        final Org org = optionalOrg.get();

        if (!org.isActive) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Org already rejected");
        }

        org.isActive = false;
        orgService.update(org);
        mailService.sendEmail(command.reason, command.email);

        return new Result<>(new OrgRejected(org.id.toString()));
    }
}