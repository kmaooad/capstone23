package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.AcceptInvitationLink;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class AcceptInvitationLinkHandler implements CommandHandler<AcceptInvitationLink, ExpertCreated> {



    @Override
    public Result<ExpertCreated> handle(AcceptInvitationLink command) {
        return null;
    }
}
