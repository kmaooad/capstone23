package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.AcceptInvitationLink;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertInvitationRepository;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import edu.kmaooad.capstone23.experts.service.ExpertService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Objects;

@RequestScoped
public class AcceptInvitationLinkHandler implements CommandHandler<AcceptInvitationLink, ExpertCreated> {

    @Inject
    ExpertService expertService;
    @Inject
    ExpertsRepository expertsRepository;

    @Override
    public Result<ExpertCreated> handle(AcceptInvitationLink command) {
        var packedInvitationObject = expertService.findInvitationById(command.getInvitationId());
        if (packedInvitationObject.isEmpty()) {
           return new Result<>(ErrorCode.NOT_FOUND, String.format("Invitation id %s not found", command.getInvitationId()));
        }
        var invitationObject = packedInvitationObject.get();
        var expert = new Expert();
        expert.name = invitationObject.expertName;
        expert.org = invitationObject.org;
        expertsRepository.insert(expert);
        return new Result<>(new ExpertCreated(expert.id.toString(), expert.org));
    }
}
