package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.AssignExpertToMember;
import edu.kmaooad.capstone23.experts.events.ExpertAssigned;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class AssignExpertToMemberHandlerTest {

    @Inject
    CommandHandler<CreateOrg, OrgCreated> orgHandler;

    @Inject
    CommandHandler<AssignExpertToMember, ExpertAssigned> assignedCommandHandler;

    @Inject
    CommandHandler<CreateBasicMember, BasicMemberCreated> memberCreatedCommandHandler;
    @Test
    public void testSuccessfulHandling() {
        AssignExpertToMember assignExpertToMemberCommand = new AssignExpertToMember();
        assignExpertToMemberCommand.setMemberId(createTestMember());

        Result<ExpertAssigned> result = assignedCommandHandler.handle(assignExpertToMemberCommand);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getMemberId().isEmpty());
    }

    @Test
    public void testInvalidInput() {
        AssignExpertToMember assignExpertToMemberCommand = new AssignExpertToMember();
        assignExpertToMemberCommand.setMemberId(new ObjectId("64fe000000000a0000000000"));

        Result<ExpertAssigned> result = assignedCommandHandler.handle(assignExpertToMemberCommand);

        Assertions.assertEquals(result.getErrorCode(), ErrorCode.NOT_FOUND);
    }

    @Test
    public void testMemberIsExpert() {
        AssignExpertToMember assignExpertToMemberCommand = new AssignExpertToMember();
        assignExpertToMemberCommand.setMemberId(createTestExpertMember());

        Result<ExpertAssigned> result = assignedCommandHandler.handle(assignExpertToMemberCommand);

        Assertions.assertEquals(result.getErrorCode(), ErrorCode.CONFLICT);
    }


    private ObjectId createTestMember() {
        CreateOrg orgCommand = new CreateOrg();
        orgCommand.setOrgName("Super Duper Create Team");
        orgCommand.industry = "Some random industry";
        orgCommand.website = "Some random website";

        CreateBasicMember memberCommand = new CreateBasicMember();
        memberCommand.setFirstName("First");
        memberCommand.setLastName("Last");
        memberCommand.setEmail("mail@test.com");
        memberCommand.setIsExpert("false");
        System.out.println(orgHandler.handle(orgCommand).getErrorCode());
        System.out.println(orgHandler.handle(orgCommand).getMessage());
        memberCommand.setOrgId(new ObjectId(orgHandler.handle(orgCommand).getValue().getOrgId()));

        return new ObjectId(memberCreatedCommandHandler.handle(memberCommand).getValue().getMemberId());
    }

    private ObjectId createTestExpertMember() {
        CreateOrg orgCommand = new CreateOrg();
        orgCommand.setOrgName("Super Duper Create Team");
        orgCommand.industry = "Some random industry";
        orgCommand.website = "Some random website";

        CreateBasicMember memberCommand = new CreateBasicMember();
        memberCommand.setFirstName("First");
        memberCommand.setLastName("Last");
        memberCommand.setEmail("mail@test.com");
        memberCommand.setOrgId(new ObjectId(orgHandler.handle(orgCommand).getValue().getOrgId()));
        memberCommand.setIsExpert("true");

        return new ObjectId(memberCreatedCommandHandler.handle(memberCommand).getValue().getMemberId());
    }
}
