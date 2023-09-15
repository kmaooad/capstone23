package edu.kmaooad.capstone23.orgs.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.UpdateMember;
import edu.kmaooad.capstone23.members.events.MemberUpdated;
import edu.kmaooad.capstone23.orgs.members.TestWithOrgSetUp;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateMemberHandlerTest extends TestWithOrgSetUp {
    @Inject
    CommandHandler<UpdateMember, MemberUpdated> handler;

    @Test
    @DisplayName("Update member: Basic handling")
    void testSuccessfulHandling() {
        UpdateMember command = new UpdateMember();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setOrgId(createdOrgId);
        command.setEmail("email@email123.com");
        command.setId(new ObjectId(createMember()));

        Result<MemberUpdated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(result.getValue().getId(), command.getId());
    }

    @Test
    @DisplayName("Update Member: Email validation handling")
    void testEmailValidation() {
        UpdateMember command = new UpdateMember();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setOrgId(createdOrgId);
        command.setEmail("email.com");
        command.setId(new ObjectId(createMember()));

        Result<MemberUpdated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
    }

    @Test
    @DisplayName("Update Member: Email uniqueness validation handling")
    void testEmailUniquenessValidation() {
        UpdateMember command = new UpdateMember();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setOrgId(createdOrgId);
        command.setEmail("email@email.com1");
        command.setId(new ObjectId(createMember()));

        Result<MemberUpdated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
    }

    @Test
    @DisplayName("Update Member: non-existent member")
    void testPriorRecordExistence() {
        UpdateMember command = new UpdateMember();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setOrgId(createdOrgId);
        command.setEmail("email@email12233.com1");
        command.setId(new ObjectId());

        Result<MemberUpdated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.NOT_FOUND, result.getErrorCode());
    }
}
