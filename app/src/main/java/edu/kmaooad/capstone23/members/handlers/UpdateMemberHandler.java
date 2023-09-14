package edu.kmaooad.capstone23.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.UpdateMember;
import edu.kmaooad.capstone23.members.events.MemberUpdated;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class UpdateMemberHandler implements CommandHandler<UpdateMember, MemberUpdated> {
    @Override
    public Result<MemberUpdated> handle(UpdateMember command) {
        return null;
    }
}
