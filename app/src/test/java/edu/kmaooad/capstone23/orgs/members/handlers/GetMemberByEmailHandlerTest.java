package edu.kmaooad.capstone23.orgs.members.handlers;

import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.GetMemberByEmail;
import edu.kmaooad.capstone23.members.events.MemberUpdated;
import edu.kmaooad.capstone23.members.handlers.GetMemberByEmailHandler;
import edu.kmaooad.capstone23.orgs.members.TestWithMembersSetUp;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class GetMemberByEmailHandlerTest extends TestWithMembersSetUp {

    @Inject
    GetMemberByEmailHandler handler;

    private final String email = "anotherEmail@gmail.com";

    @BeforeEach
    void setUp() {
        createOrgWithMember(email);
    }

    @Test
    @DisplayName("Read member: Basic handling")
    void testSuccessfulHandling() {
        GetMemberByEmail command = new GetMemberByEmail();
        command.setEmail(email);

        Result<MemberUpdated> result = handler.handle(command);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertNotNull(result.getValue().getId());
        Assertions.assertEquals(email, result.getValue().getEmail());
    }

    @Test
    @DisplayName("Read member: Wrong email handling")
    void testWrongEmailHandling() {
        GetMemberByEmail command = new GetMemberByEmail();
        command.setEmail(email + "moreSymbols");

        Result<MemberUpdated> result = handler.handle(command);
        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.NOT_FOUND, result.getErrorCode());
    }
}