package edu.kmaooad.capstone23.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.CreateMemberBasic;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.members.events.CreatedMemberBasic;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@RequestScoped
public class CreateMembersHandlerBasic implements CommandHandler<CreateMemberBasic, CreatedMemberBasic> {
    @Inject
    MembersRepository membersRepository;

    @Inject
    OrgsRepository orgsRepository;

    @Override
    public Result<CreatedMemberBasic> handle(CreateMemberBasic command) {
        try {
            Member member = new Member();
            member.firstName = command.getFirstName();
            member.lastName = command.getLastName();
            member.email = command.getEmail();
            member.orgId = new ObjectId(command.getOrgId());
            Optional<Org> memberOrg = orgsRepository.findByIdOptional(member.orgId);
            if (memberOrg.isEmpty())
                return new Result<>(ErrorCode.VALIDATION_FAILED, "Organisation not found");
            membersRepository.insert(member);
            CreatedMemberBasic result = new CreatedMemberBasic(member.id.toString());
            return new Result<>(result);
        } catch (IllegalArgumentException e) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Invalid org id provided");
        }
    }
}
