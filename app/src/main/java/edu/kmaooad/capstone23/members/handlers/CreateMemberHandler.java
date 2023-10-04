package edu.kmaooad.capstone23.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.members.exceptions.UniquenessViolationException;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@RequestScoped
public class CreateMemberHandler implements CommandHandler<CreateBasicMember, BasicMemberCreated> {
    @Inject
    MembersRepository membersRepository;
    @Inject
    ExpertsRepository expertsRepository;
    @Inject
    OrgsRepository orgsRepository;

    @Override
    public Result<BasicMemberCreated> handle(CreateBasicMember command) {
        try {
            Member member = new Member();
            member.firstName = command.getFirstName();
            member.lastName = command.getLastName();
            member.email = command.getEmail();
            member.orgId = List.of(command.getOrgId());
            member.isExpert = Boolean.parseBoolean(command.getIsExpert());
            Optional<Org> memberOrg = orgsRepository.findByIdOptional(command.getOrgId());
            if (memberOrg.isEmpty())
                return new Result<>(ErrorCode.VALIDATION_FAILED, "Organisation not found");
            membersRepository.insert(member);
            if (member.isExpert) {
                Expert expert = new Expert();
                expert.memberId = member.id;
                expert.name = member.firstName + " " + member.lastName;
                expert.org = memberOrg.get();
                expertsRepository.insert(expert);
            }
            BasicMemberCreated result = new BasicMemberCreated(member.id.toString());
            return new Result<>(result);
        } catch (IllegalArgumentException e) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Invalid org id provided");
        } catch (UniquenessViolationException e) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Email is not unique");
        }
    }
}
