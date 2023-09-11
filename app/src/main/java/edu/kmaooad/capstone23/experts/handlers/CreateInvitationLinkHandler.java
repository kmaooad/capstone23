package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.CreateInvitationLink;
import edu.kmaooad.capstone23.experts.events.InvitationLinkCreated;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class CreateInvitationLinkHandler implements CommandHandler<CreateInvitationLink, InvitationLinkCreated> {



    @Override
    public Result<InvitationLinkCreated> handle(CreateInvitationLink command) {
        return null;
    }
}
