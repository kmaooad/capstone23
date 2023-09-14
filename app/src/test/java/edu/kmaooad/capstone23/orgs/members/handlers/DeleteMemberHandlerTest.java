package edu.kmaooad.capstone23.orgs.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.commands.DeleteMember;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.members.events.MemberDeleted;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.members.ClearDbMemberTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeleteMemberHandlerTest extends ClearDbMemberTest {
    @Inject
    CommandHandler<DeleteMember, MemberDeleted> deleteHandler;
    @Inject
    CommandHandler<CreateBasicMember, BasicMemberCreated> createHandler;
    @Inject
    OrgsRepository orgsRepository;
    private ObjectId createdOrgId;

    @BeforeEach
    void setUp() {
        orgsRepository.deleteAll();
        var org = new Org();
        org.name = "NaUKMA";
        orgsRepository.insert(org);
        createdOrgId = org.id;
    }

    @Test
    void testSuccessfulHandling() {
        CreateBasicMember command = new CreateBasicMember();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setOrgId(createdOrgId);
        command.setEmail("email@email.com4343");

        Result<BasicMemberCreated> result = createHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getMemberId().isEmpty());
        Assertions.assertTrue(deleteHandler.handle(new DeleteMember(result.getValue().getMemberId())).getValue().isSuccess());
    }
}
