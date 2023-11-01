package edu.kmaooad.capstone23.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.abstractions.MembersRepository;
import edu.kmaooad.capstone23.members.dto.OrgDTO;
import edu.kmaooad.capstone23.members.dto.UserDTO;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.members.exceptions.UniquenessViolationException;
import edu.kmaooad.capstone23.members.services.ExpertsService;
import edu.kmaooad.capstone23.members.services.OrgService;
import edu.kmaooad.capstone23.members.services.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;


import java.util.Optional;

@RequestScoped
public class CreateMemberHandler implements CommandHandler<CreateBasicMember, BasicMemberCreated> {
    @Inject
    MembersRepository membersRepository;
    @Inject
    ExpertsService expertsService;
    @Inject
    OrgService orgService;
    @Inject
    UserService userService;

    @Override
    public Result<BasicMemberCreated> handle(CreateBasicMember command) {
        try {
            Member member = new Member();

            UserDTO foundOrCreatedUser = userService
                    .findByEmail(command.getEmail())
                    .orElseGet(() ->
                            userService.createUser(
                                    command.getFirstName(),
                                    command.getLastName(),
                                    command.getEmail()
                            )
                    );
            member.orgId = command.getOrgId();
            member.userId = foundOrCreatedUser.getId();
            member.isExpert = Boolean.parseBoolean(command.getIsExpert());
            Optional<OrgDTO> memberOrg = orgService.findByIdOptional(command.getOrgId());
            if (memberOrg.isEmpty())
                return new Result<>(ErrorCode.VALIDATION_FAILED, "Organisation not found");
            membersRepository.insert(member);
            if (member.isExpert) {
                expertsService.createExpertFromMember(command, member, memberOrg);
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
