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
import edu.kmaooad.capstone23.users.commands.CreateUser;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.handlers.CreateUserHandler;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@RequestScoped
public class CreateMemberHandler implements CommandHandler<CreateBasicMember, BasicMemberCreated> {
    @Inject
    MembersRepository membersRepository;
    @Inject
    ExpertsRepository expertsRepository;
    @Inject
    OrgsRepository orgsRepository;
    @Inject
    CreateUserHandler createUserHandler;

    @Inject
    UserRepository userRepository;

    @Override
    public Result<BasicMemberCreated> handle(CreateBasicMember command) {
        try {
            Member member = new Member();
            var newCreateUserCommand = new CreateUser();
            newCreateUserCommand.setEmail(command.getEmail());
            newCreateUserCommand.setFirstName(command.getFirstName());
            newCreateUserCommand.setLastName(command.getLastName());
            var resultOfUserCreation = userRepository.findByEmail(command.getEmail())
                    .orElseGet(() -> {
                        var user = new User();
                        user.id = new ObjectId(createUserHandler.handle(newCreateUserCommand).getValue().getId());
                        return user;
                    });
            member.orgId = command.getOrgId();
            member.userId = resultOfUserCreation.id;
            member.isExpert = Boolean.parseBoolean(command.getIsExpert());
            Optional<Org> memberOrg = orgsRepository.findByIdOptional(command.getOrgId());
            if (memberOrg.isEmpty())
                return new Result<>(ErrorCode.VALIDATION_FAILED, "Organisation not found");
            membersRepository.insert(member);
            if (member.isExpert) {
                Expert expert = new Expert();
                expert.memberId = member.id;
                expert.name = command.getFirstName() + " " + command.getLastName();
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
