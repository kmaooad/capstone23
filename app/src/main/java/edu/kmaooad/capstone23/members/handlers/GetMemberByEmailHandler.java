package edu.kmaooad.capstone23.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.GetMemberByEmail;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.members.events.MemberRead;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class GetMemberByEmailHandler implements CommandHandler<GetMemberByEmail, MemberRead> {
    @Inject
    MembersRepository membersRepository;

    @Override
    public Result<MemberRead> handle(GetMemberByEmail command) {
        var member = membersRepository.findMemberByEmail(command.getEmail());
        return member
                .map(value -> new Result<>(new MemberRead(value)))
                .orElseGet(() -> new Result<>(ErrorCode.NOT_FOUND, "Member not found"));
    }
}
