package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.CreateInvitationLink;
import edu.kmaooad.capstone23.experts.dal.ExpertInvitation;
import edu.kmaooad.capstone23.experts.dal.ExpertInvitationRepository;
import edu.kmaooad.capstone23.experts.events.InvitationLinkCreated;
import edu.kmaooad.capstone23.experts.service.ExpertInvitationMailService;
import edu.kmaooad.capstone23.experts.service.ExpertService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateInvitationLinkHandler implements CommandHandler<CreateInvitationLink, InvitationLinkCreated> {

    @Inject
    ExpertInvitationRepository repository;
    @Inject
    ExpertService expertService;
    @Inject
    ExpertInvitationMailService mailService;

    @Override
    public Result<InvitationLinkCreated> handle(CreateInvitationLink command) {
        var expertInvitation = new ExpertInvitation();
        expertInvitation.email = command.getEmail();
        expertInvitation.expertType = command.getExpertType();
        repository.persist(expertInvitation);
        var invitationLink = expertService.createInvitationLink(expertInvitation.id);
        mailService.sendInvitationLink(expertInvitation.email, invitationLink);
        return new Result<>(new InvitationLinkCreated(invitationLink));
    }
}
