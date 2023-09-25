package edu.kmaooad.capstone23.orgs.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.commands.DeleteMember;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.members.events.MemberDeleted;
import edu.kmaooad.capstone23.orgs.members.TestWithOrgSetUp;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeleteMemberHandlerTest extends TestWithOrgSetUp {
    @Inject
    CommandHandler<DeleteMember, MemberDeleted> deleteHandler;
    @Inject
    CommandHandler<CreateBasicMember, BasicMemberCreated> createHandler;

    @Test
    void testSuccessfulHandling() {
        CreateBasicMember command = new CreateBasicMember();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setOrgId(createdOrgId);
        command.setEmail("email@email.com");
        command.setIsExpert("false");

        Result<BasicMemberCreated> result = createHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getMemberId().isEmpty());
        Assertions.assertTrue(deleteHandler.handle(new DeleteMember(result.getValue().getMemberId())).getValue().isSuccess());
    }
}
