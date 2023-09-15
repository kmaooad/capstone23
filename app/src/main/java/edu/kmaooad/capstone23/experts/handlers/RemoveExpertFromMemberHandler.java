package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.DeleteExpert;
import edu.kmaooad.capstone23.experts.commands.RemoveExpertFromMember;
import edu.kmaooad.capstone23.experts.events.ExpertDeleted;
import edu.kmaooad.capstone23.experts.events.ExpertRemovedFromMember;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class RemoveExpertFromMemberHandler
        implements CommandHandler<RemoveExpertFromMember, ExpertRemovedFromMember> {
    @Inject
    private MembersRepository membersRepository;

    @Override
    public Result<ExpertRemovedFromMember> handle(RemoveExpertFromMember command) {
        ObjectId id = command.getMemberId();
        if (id != null) {
            Member member = membersRepository.findById(id);
            if (!member.isExpert) {
                return new Result<>(ErrorCode.NOT_FOUND, "Expert not found");
            }
            member.isExpert = false;
            membersRepository.update(member);
        } else {
            return new Result<>(ErrorCode.NOT_FOUND, "Member not found");
        }
        return null;
    }
}
