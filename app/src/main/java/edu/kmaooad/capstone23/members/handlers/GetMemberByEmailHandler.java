package edu.kmaooad.capstone23.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.GetMemberByEmail;
import edu.kmaooad.capstone23.members.dal.abstractions.MembersRepository;
import edu.kmaooad.capstone23.members.events.MembersListed;
import edu.kmaooad.capstone23.members.services.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;

@RequestScoped
public class GetMemberByEmailHandler implements CommandHandler<GetMemberByEmail, MembersListed> {
    @Inject
    MembersRepository membersRepository;

    @Inject
    UserService userService;

    @Override
    public Result<MembersListed> handle(GetMemberByEmail command) {
        var user = userService.findByEmail(command.getEmail());
        var result = new MembersListed();
        if (user.isEmpty()) {
            result.setMembers(new ArrayList<>());
            return new Result<>(result);
        }
        var members = membersRepository.getByUser(user.get());
        result.setMembers(members);
        return new Result<>(result);
    }
}
