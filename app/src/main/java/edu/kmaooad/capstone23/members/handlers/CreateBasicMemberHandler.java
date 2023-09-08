package edu.kmaooad.capstone23.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class CreateBasicMemberHandler implements CommandHandler<CreateBasicMember, BasicMemberCreated> {
    @Inject
    MembersRepository membersRepository;

    @Inject
    OrgsRepository orgsRepository;

    @Override
    public Result<BasicMemberCreated> handle(CreateBasicMember command) {
        try {
            Member member = new Member();
            member.firstName = command.getFirstName();
            member.lastName = command.getLastName();
            member.email = command.getEmail();
            member.orgId = command.getOrgId();
            Optional<Org> memberOrg = orgsRepository.findByIdOptional(member.orgId);
            if (memberOrg.isEmpty())
                return new Result<>(ErrorCode.VALIDATION_FAILED, "Organisation not found");
            membersRepository.insert(member);
            BasicMemberCreated result = new BasicMemberCreated(member.id.toString());
            return new Result<>(result);
        } catch (IllegalArgumentException e) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Invalid org id provided");
        }
    }
}
