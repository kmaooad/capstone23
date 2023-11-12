package edu.kmaooad.capstone23.orgs.members.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.GetMemberByEmail;
import edu.kmaooad.capstone23.members.events.MembersListed;
import edu.kmaooad.capstone23.members.handlers.GetMemberByEmailHandler;
import edu.kmaooad.capstone23.orgs.members.TestWithMembersSetUp;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class GetMemberByEmailHandlerTest extends TestWithMembersSetUp {

    @Inject
    GetMemberByEmailHandler handler;

    private final String email = "anotherEmail@gmail.com";

    @Test
    @DisplayName("Read member: Basic handling")
    void testSuccessfulHandling() {
        userService.deleteByEmail(email);
        createOrgWithMember(email);
        GetMemberByEmail command = new GetMemberByEmail();
        command.setEmail(email);

        Result<MembersListed> result = handler.handle(command);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertNotNull(result.getValue().getMembers());
    }

    @Test
    @DisplayName("Read member: Wrong email handling")
    void testWrongEmailHandling() {
        createOrgWithMember(email);
        GetMemberByEmail command = new GetMemberByEmail();
        command.setEmail(email + "moreSymbols");

        Result<MembersListed> result = handler.handle(command);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertTrue(result.getValue().getMembers().isEmpty());
    }
}
