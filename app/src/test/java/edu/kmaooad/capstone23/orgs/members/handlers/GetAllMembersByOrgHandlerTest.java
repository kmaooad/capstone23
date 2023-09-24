package edu.kmaooad.capstone23.orgs.members.handlers;

import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.GetAllMembersByOrg;
import edu.kmaooad.capstone23.members.events.MembersListed;
import edu.kmaooad.capstone23.members.handlers.GetAllMembersByOrgHandler;
import edu.kmaooad.capstone23.orgs.members.TestWithMembersSetUp;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class GetAllMembersByOrgHandlerTest extends TestWithMembersSetUp {

    @Inject
    GetAllMembersByOrgHandler handler;

    @Test
    @DisplayName("Read all members by org: Basic handling")
    void testSuccessfulHandling() {
        GetAllMembersByOrg firstOrgCommand = new GetAllMembersByOrg();
        firstOrgCommand.setOrgId(firstOrg);
        firstOrgCommand.setPage(0);
        firstOrgCommand.setSize(3);

        Result<MembersListed> firstOrgResult = handler.handle(firstOrgCommand);
        Assertions.assertTrue(firstOrgResult.isSuccess());
        Assertions.assertNotNull(firstOrgResult.getValue());
        Assertions.assertEquals(
                firstOrgMembers.stream().sorted().toList(),
                firstOrgResult.getValue().getMembers().stream().map(member -> member.id).sorted().toList()
        );

        GetAllMembersByOrg secondOrgCommand = new GetAllMembersByOrg();
        secondOrgCommand.setOrgId(secondOrg);
        secondOrgCommand.setPage(0);
        secondOrgCommand.setSize(1);

        Result<MembersListed> secondOrgResult = handler.handle(secondOrgCommand);
        Assertions.assertTrue(secondOrgResult.isSuccess());
        Assertions.assertNotNull(secondOrgResult.getValue());
        Assertions.assertEquals(
                secondOrgMembers.stream().sorted().toList(),
                secondOrgResult.getValue().getMembers().stream().map(member -> member.id).sorted().toList()
        );
    }

    @Test
    @DisplayName("Read all members by org: Small page handling")
    void testSmallPageHandling() {
        var objectsLeft = firstOrgMembers;
        for(int i = 0; i < 3; i ++) {
            GetAllMembersByOrg command = new GetAllMembersByOrg();
            command.setOrgId(firstOrg);
            command.setPage(i);
            command.setSize(1);

            Result<MembersListed> result = handler.handle(command);
            Assertions.assertTrue(result.isSuccess());
            Assertions.assertNotNull(result.getValue());

            var resultMembers = result.getValue().getMembers();
            Assertions.assertEquals(1, resultMembers.size());
            Assertions.assertTrue(objectsLeft.contains(resultMembers.get(0).id));

            objectsLeft.remove(resultMembers.get(0).id);
        }
        Assertions.assertTrue(objectsLeft.isEmpty());

        GetAllMembersByOrg command = new GetAllMembersByOrg();
        command.setOrgId(firstOrg);
        command.setPage(4);
        command.setSize(1);

        Result<MembersListed> result = handler.handle(command);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertTrue(result.getValue().getMembers().isEmpty());
    }

    @Test
    @DisplayName("Read all members by org: wrong page handling")
    void testWrongPageHandling() {
        GetAllMembersByOrg command = new GetAllMembersByOrg();
        command.setOrgId(firstOrg);
        command.setPage(-5);
        command.setSize(3);

        Result<MembersListed> result = handler.handle(command);
        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
    }

    @Test
    @DisplayName("Read all members: wrong size handling")
    void testWrongSizeHandling() {
        GetAllMembersByOrg command = new GetAllMembersByOrg();
        command.setOrgId(firstOrg);
        command.setPage(0);
        command.setSize(-5);

        Result<MembersListed> result = handler.handle(command);
        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
    }
}