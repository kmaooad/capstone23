package edu.kmaooad.capstone23.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.GetAllMembersByOrg;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.members.events.MembersListed;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class GetAllMembersByOrgHandler implements CommandHandler<GetAllMembersByOrg, MembersListed> {
    @Inject
    MembersRepository membersRepository;

    @Override
    public Result<MembersListed> handle(GetAllMembersByOrg command) {
        List<Member> membersPage =
                membersRepository
                        .find("orgId", command.getOrgId())
                        .page(Page.of(command.getPage(), command.getSize()))
                        .list();
        MembersListed result = new MembersListed();
        result.setMembers(membersPage);
        return new Result<>(result);
    }
}
