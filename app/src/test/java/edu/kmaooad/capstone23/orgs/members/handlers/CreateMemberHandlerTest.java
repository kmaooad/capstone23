package edu.kmaooad.capstone23.orgs.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.orgs.members.TestWithOrgSetUp;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateMemberHandlerTest extends TestWithOrgSetUp {
    @Inject
    ExpertsRepository expertsRepository;
    @Inject
    CommandHandler<CreateBasicMember, BasicMemberCreated> handler;

    @Test
    void testSuccessfulHandling() {
        CreateBasicMember command = new CreateBasicMember();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setOrgId(createdOrgId);
        command.setEmail("email@email.com");
        command.setIsExpert("false");

        Result<BasicMemberCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getMemberId().isEmpty());
    }

    @Test
    void testEmailValidation() {
        CreateBasicMember command = new CreateBasicMember();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setOrgId(createdOrgId);
        command.setEmail("email.com");
        command.setIsExpert("false");

        Result<BasicMemberCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
    }

    @Test
    void testEmailUniquenessValidation() {
        CreateBasicMember command = new CreateBasicMember();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setOrgId(createdOrgId);
        command.setEmail("email@email.com");

        handler.handle(command);

        command = new CreateBasicMember();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setOrgId(createdOrgId);
        command.setEmail("email@email.com");

        Result<BasicMemberCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
    }

    @Test
    @DisplayName("Create expert member: expert created")
    void testExpertCreated() {
        var command = new CreateBasicMember();
        command.setFirstName("firstName");
        command.setLastName("LastName");
        command.setOrgId(createdOrgId);
        command.setEmail("testCreateExpertAfterMemberCreated@gmail.com");
        command.setIsExpert("true");

        Result<BasicMemberCreated> result = handler.handle(command);
        Assertions.assertTrue(result.isSuccess());
        var member = result.getValue();

        var expert = expertsRepository.findByMemberId(new ObjectId(member.getMemberId()));
        Assertions.assertNotNull(expert);
        Assertions.assertNotNull(expert.id);
        Assertions.assertEquals(expert.org.id, createdOrgId);
    }
}
