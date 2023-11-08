package edu.kmaooad.capstone23.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.GetAllMembers;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.abstractions.MembersRepository;
import edu.kmaooad.capstone23.members.events.MembersListed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class GetAllMembersHandler implements CommandHandler<GetAllMembers, MembersListed> {
    @Inject
    MembersRepository membersRepository;

    @Override
    public Result<MembersListed> handle(GetAllMembers command) {
        List<Member> membersPage = membersRepository.getAll(command);
        MembersListed result = new MembersListed();
        result.setMembers(membersPage);
        return new Result<>(result);
    }
}
