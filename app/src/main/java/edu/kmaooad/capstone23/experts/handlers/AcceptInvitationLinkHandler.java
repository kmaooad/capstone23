package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.AcceptInvitationLink;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertInvitationRepository;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import edu.kmaooad.capstone23.experts.notification.EventDispatcher;
import edu.kmaooad.capstone23.experts.notification.EventType;
import edu.kmaooad.capstone23.experts.notification.model.TelegramEvent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Objects;

@RequestScoped
public class AcceptInvitationLinkHandler implements CommandHandler<AcceptInvitationLink, ExpertCreated> {

    @Inject
    ExpertInvitationRepository invitationRepository;
    @Inject
    ExpertsRepository expertsRepository;

    @Inject
    EventDispatcher eventDispatcher;

    @Override
    public Result<ExpertCreated> handle(AcceptInvitationLink command) {
        var invitationObject = invitationRepository.findById(command.getInvitationId());
        if (Objects.isNull(invitationObject)) {
           return new Result<>(ErrorCode.NOT_FOUND, String.format("Invitation id %s not found", command.getInvitationId()));
        }
        var expert = new Expert();
        expert.name = invitationObject.expertName;
        expert.org = invitationObject.org;
        expertsRepository.insert(expert);
        eventDispatcher.notify(EventType.TELEGRAM, new TelegramEvent(EventType.TELEGRAM, "telegramId", "User accepted link"));
        return new Result<>(new ExpertCreated(expert.id.toString(), expert.org));
    }
}
