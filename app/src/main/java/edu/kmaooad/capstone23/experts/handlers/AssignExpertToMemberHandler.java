package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.AssignExpertToMember;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertAssigned;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.services.OrgsService;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class AssignExpertToMemberHandler implements CommandHandler<AssignExpertToMember, ExpertAssigned> {

    @Inject
    private MembersRepository membersRepository;
    @Inject
    private ExpertsRepository expertsRepository;
    @Inject
    OrgsService orgsService;
    @Inject
    private UserRepository userRepository;

    public Result<ExpertAssigned> handle(AssignExpertToMember command) {
        ObjectId id = command.getMemberId();
        Member member = membersRepository.findById(id);

        if (member == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Member not found");
        }

        if (member.isExpert) {
            return new Result<>(ErrorCode.CONFLICT, "Member is already an expert");
        }

        member.isExpert = true;
        membersRepository.updateEntry(member);

        Org org = orgsService.findById(member.orgId.toString());
        Expert expert = new Expert();
        var user = userRepository.findById(member.userId);
        expert.name = user.firstName + user.lastName;
        expert.org = org;

        expertsRepository.insert(expert);

        return new Result<>(new ExpertAssigned(member.id.toString()));
    }
}
