package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.CreateInvitationLink;
import edu.kmaooad.capstone23.experts.dal.ExpertInvitation;
import edu.kmaooad.capstone23.experts.dal.ExpertInvitationRepository;
import edu.kmaooad.capstone23.experts.events.InvitationLinkCreated;
import edu.kmaooad.capstone23.experts.service.ExpertInvitationMailService;
import edu.kmaooad.capstone23.experts.service.ExpertService;
import edu.kmaooad.capstone23.orgs.services.OrgsService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import static edu.kmaooad.capstone23.common.ErrorCode.VALIDATION_FAILED;

@RequestScoped
public class CreateInvitationLinkHandler implements CommandHandler<CreateInvitationLink, InvitationLinkCreated> {

    @Inject
    ExpertInvitationRepository repository;
    @Inject
    ExpertService expertService;
    @Inject
    ExpertInvitationMailService mailService;
    @Inject
    OrgsService orgsService;

    @Override
    public Result<InvitationLinkCreated> handle(CreateInvitationLink command) {
        var expertInvitation = new ExpertInvitation();
        expertInvitation.email = command.getEmail();
        expertInvitation.expertType = command.getExpertType();
        expertInvitation.org = orgsService.findByName(command.getOrgName());
        expertInvitation.expertName = command.getExpertName();

        if (expertInvitation.org == null) {
            return new Result<>(VALIDATION_FAILED, "Wrong organisation name");
        }

        repository.persist(expertInvitation);
        var invitationLink = expertService.createInvitationLink(expertInvitation.id);
        mailService.sendInvitationLink(expertInvitation.email, invitationLink);
        return new Result<>(new InvitationLinkCreated(invitationLink));
    }
}
