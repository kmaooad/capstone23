package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.RemoveExpertFromMember;
import edu.kmaooad.capstone23.experts.events.ExpertRemovedFromMember;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import edu.kmaooad.capstone23.users.interfaces.services.UserService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class RemoveExpertFromMemberHandlerTest {

    @Inject
    CommandHandler<RemoveExpertFromMember, ExpertRemovedFromMember> removeHandler;
    @Inject
    CommandHandler<CreateBasicMember, BasicMemberCreated> memberHandler;
    @Inject
    CommandHandler<CreateOrg, OrgCreated> orgHandler;
    @Inject
    MembersRepository membersRepository;

    @Inject
    UserService userService;

    @Test
    @DisplayName("Remove Expert From Member Handler: Basic")
    public void testSuccessfulHandling() {
        ObjectId memberId = createExpertMember();

        RemoveExpertFromMember command = new RemoveExpertFromMember();
        command.setMemberId(memberId);

        Result<ExpertRemovedFromMember> result = removeHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(membersRepository.findById(memberId).isExpert);
    }

    @Test
    @DisplayName("Remove Expert From Member Handler: the member is already not an expert")
    public void testInvalidHandling() {
        ObjectId memberId = createBasicMember();

        RemoveExpertFromMember command = new RemoveExpertFromMember();
        command.setMemberId(memberId);

        Result<ExpertRemovedFromMember> result = removeHandler.handle(command);

        Assertions.assertEquals(result.getErrorCode(), ErrorCode.NOT_FOUND);
    }

    private ObjectId createExpertMember() {
        CreateOrg orgCommand = new CreateOrg();
        orgCommand.setOrgName("Dobra Komanda");
        orgCommand.industry = "Some random industry";
        orgCommand.website = "Some random website";
        Result<OrgCreated> orgResult = orgHandler.handle(orgCommand);

        CreateBasicMember memberCommand = new CreateBasicMember();
        memberCommand.setFirstName("Vasyl");
        memberCommand.setLastName("Pupok");
        memberCommand.setOrgId(orgResult.getValue().getOrgId());
        memberCommand.setEmail("ultra.poshta@ukr.net");
        userService.deleteByEmail(memberCommand.getEmail());
        memberCommand.setIsExpert("true");

        Result<BasicMemberCreated> result = memberHandler.handle(memberCommand);

        return new ObjectId(result.getValue().getMemberId());
    }

    private ObjectId createBasicMember() {
        CreateOrg orgCommand = new CreateOrg();
        orgCommand.setOrgName("Pohana Komanda");
        orgCommand.industry = "Some random industry";
        orgCommand.website = "Some random website";
        Result<OrgCreated> orgResult = orgHandler.handle(orgCommand);

        CreateBasicMember memberCommand = new CreateBasicMember();
        memberCommand.setFirstName("Vasyleus");
        memberCommand.setLastName("Pupochello");
        memberCommand.setOrgId(orgResult.getValue().getOrgId());
        memberCommand.setEmail("ultra.poshta.proksima@ukr.net");
        userService.deleteByEmail(memberCommand.getEmail());
        memberCommand.setIsExpert("false");

        Result<BasicMemberCreated> result = memberHandler.handle(memberCommand);

        return new ObjectId(result.getValue().getMemberId());
    }

}
