package edu.kmaooad.capstone23.members.handlers;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.dal.EntityBanRepository;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.UpdateMember;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.members.events.MemberUpdated;
import edu.kmaooad.capstone23.members.exceptions.UniquenessViolationException;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class UpdateMemberHandler implements CommandHandler<UpdateMember, MemberUpdated> {
    @Inject
    MembersRepository membersRepository;

    @Inject
    OrgsRepository orgsRepository;

    @Inject
    EntityBanRepository banRepository;

    @Override
    public Result<MemberUpdated> handle(UpdateMember command) {
        try {
            Member member = new Member();
            member.firstName = command.getFirstName();
            member.lastName = command.getLastName();
            member.email = command.getEmail();
            member.orgId = command.getOrgId();
            member.id = command.getId();
            Optional<Member> existingEntryForMember = membersRepository.findByIdOptional(member.id);
            if (member.orgId.stream().anyMatch(objectId -> orgsRepository.findByIdOptional(objectId).isEmpty()))
                return new Result<>(ErrorCode.VALIDATION_FAILED, "Organisation not found");
            if (existingEntryForMember.isEmpty())
                return new Result<>(ErrorCode.NOT_FOUND, "Member not found");
            if(banRepository.findForEntity(BannedEntityType.Member, member.id).isPresent())
                return new Result<>(ErrorCode.EXCEPTION, "Member is banned");
            membersRepository.updateEntry(member);
            MemberUpdated result = new MemberUpdated(member);
            return new Result<>(result);
        } catch (IllegalArgumentException e) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Invalid org id provided");
        } catch (UniquenessViolationException e) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Email is not unique");
        }
    }
}
