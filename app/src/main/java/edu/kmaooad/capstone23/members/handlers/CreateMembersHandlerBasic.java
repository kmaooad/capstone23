package edu.kmaooad.capstone23.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.CreateMemberBasic;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.members.events.CreatedMemberBasic;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

public class CreateMembersHandlerBasic implements CommandHandler<CreateMemberBasic, CreatedMemberBasic> {
    @Inject
    MembersRepository membersRepository;

    @Override
    public Result<CreatedMemberBasic> handle(CreateMemberBasic command) {
        Member member = new Member();
        member.firstName = command.getFirstName();
        member.lastName = command.getLastName();
        member.email = command.getEmail();
        member.orgId = new ObjectId(command.getOrgId());
        membersRepository.insert(member);
        CreatedMemberBasic result = new CreatedMemberBasic(member.id.toString());
        return new Result<>(result);
    }
}
