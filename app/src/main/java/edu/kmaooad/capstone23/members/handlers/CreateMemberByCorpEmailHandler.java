package edu.kmaooad.capstone23.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.commands.CreateMemberByCorpEmail;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.members.services.OrgService;
import edu.kmaooad.capstone23.members.utils.CorpEmailParser;
import edu.kmaooad.capstone23.orgs.dal.Org;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class CreateMemberByCorpEmailHandler implements CommandHandler<CreateMemberByCorpEmail, BasicMemberCreated> {
    @Inject
    CommandHandler<CreateBasicMember, BasicMemberCreated> handler;
    @Inject
    OrgService orgService;
    @Inject
    CorpEmailParser corpEmailParser;

    @Override
    public Result<BasicMemberCreated> handle(CreateMemberByCorpEmail command) {
        String orgEmailDomain = corpEmailParser.getCorpEmailDomain(command.getCorpEmail());
        Optional<Org> memberOrg = orgService.findByEmailDomainOptional(orgEmailDomain);
        if (memberOrg.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Organisation not found");

        CreateBasicMember createBasicMemberCommand = new CreateBasicMember();
        createBasicMemberCommand.setFirstName(command.getFirstName());
        createBasicMemberCommand.setLastName(command.getLastName());
        createBasicMemberCommand.setEmail(command.getCorpEmail());
        createBasicMemberCommand.setIsExpert(command.getIsExpert());
        createBasicMemberCommand.setOrgId(memberOrg.get().id);

        return handler.handle(createBasicMemberCommand);
    }
}
