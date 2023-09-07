package edu.kmaooad.capstone23.orgs.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.CreateMemberBasic;
import edu.kmaooad.capstone23.members.events.CreatedMemberBasic;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateMembersHandlerBasicTest {
    @Inject
    CommandHandler<CreateMemberBasic, CreatedMemberBasic> handler;

    @Test
    void testSuccessfulHandling() {
        CreateMemberBasic command = new CreateMemberBasic();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setOrgId("64f9db2b6cf30055cb6ddcbe");
        command.setEmail("email@email.com");

        Result<CreatedMemberBasic> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getMemberId().isEmpty());
    }

    @Test
    void testEmailValidation() {
        CreateMemberBasic command = new CreateMemberBasic();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setOrgId("64f9db2b6cf30055cb6ddcbe");
        command.setEmail("email.com");

        Result<CreatedMemberBasic> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }
}
