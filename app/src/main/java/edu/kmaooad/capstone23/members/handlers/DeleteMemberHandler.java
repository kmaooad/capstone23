package edu.kmaooad.capstone23.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.DeleteMember;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.members.events.MemberDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class DeleteMemberHandler implements CommandHandler<DeleteMember, MemberDeleted> {
    @Inject
    MembersRepository membersRepository;

    @Override
    public Result<MemberDeleted> handle(DeleteMember command) {
        try {
            ObjectId objectId = command.getMemberId();
            Member entity = membersRepository.findById(objectId);
            var success = true;
            if (entity != null) {
                membersRepository.delete(entity);
            } else {
                success = false;
            }
            var result = new MemberDeleted();
            result.setSuccess(success);
            return new Result<>(result);
        } catch (IllegalArgumentException e) {
            return new Result<>(ErrorCode.EXCEPTION, "Invalid member id");
        }
    }
}