package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.AssignExpertToMember;
import edu.kmaooad.capstone23.experts.commands.CreateExpert;
import edu.kmaooad.capstone23.experts.commands.DeleteExpert;
import edu.kmaooad.capstone23.experts.events.ExpertAssigned;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import edu.kmaooad.capstone23.experts.events.ExpertDeleted;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.dal.Member;
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
        assignExpertToMemberCommand.setMemberId(new ObjectId("sonme wrong id"));

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

        CreateBasicMember memberCommand = new CreateBasicMember();
        memberCommand.setFirstName("First");
        memberCommand.setLastName("Last");
        memberCommand.setEmail("mail@test.com");
        memberCommand.setOrgId(new ObjectId(orgHandler.handle(orgCommand).getValue().getOrgId()));

        return new ObjectId(memberCreatedCommandHandler.handle(memberCommand).getValue().getMemberId());
    }

    private ObjectId createTestExpertMember() {
        CreateOrg orgCommand = new CreateOrg();
        orgCommand.setOrgName("Super Duper Create Team");

        CreateBasicMember memberCommand = new CreateBasicMember();
        memberCommand.setFirstName("First");
        memberCommand.setLastName("Last");
        memberCommand.setEmail("mail@test.com");
        memberCommand.setOrgId(new ObjectId(orgHandler.handle(orgCommand).getValue().getOrgId()));
        memberCommand.setExpert(true);

        return new ObjectId(memberCreatedCommandHandler.handle(memberCommand).getValue().getMemberId());
    }
}
