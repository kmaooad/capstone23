package edu.kmaooad.capstone23.orgs.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.CreateMemberBasic;
import edu.kmaooad.capstone23.members.commands.DeleteMember;
import edu.kmaooad.capstone23.members.events.CreatedMemberBasic;
import edu.kmaooad.capstone23.members.events.DeletedMember;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeleteMemberHandlerTest {
    @Inject
    CommandHandler<DeleteMember, DeletedMember> deleteHandler;
    @Inject
    CommandHandler<CreateMemberBasic, CreatedMemberBasic> createHandler;
    @Inject
    CommandHandler<CreateOrg, OrgCreated> orgCommandHandler;

    private ObjectId createdOrgId;

    @BeforeEach
    void setUp() {
        CreateOrg command = new CreateOrg();
        command.setOrgName("NaUKMA");

        Result<OrgCreated> result = orgCommandHandler.handle(command);
        createdOrgId = new ObjectId(result.getValue().getOrgId());
    }

    @Test
    void testSuccessfulHandling() {
        CreateMemberBasic command = new CreateMemberBasic();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setOrgId(String.valueOf(createdOrgId));
        command.setEmail("email@email.com");

        Result<CreatedMemberBasic> result = createHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getMemberId().isEmpty());
        Assertions.assertTrue(deleteHandler.handle(new DeleteMember(result.getValue().getMemberId())).getValue().isSuccess());
    }
}
