package edu.kmaooad.capstone23.orgs.handlers;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.kmaooad.capstone23.common.*;
import edu.kmaooad.capstone23.orgs.commands.DeleteOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.OrgDeleted;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class DeleteOrgHandlerTest {

    @Inject
    CommandHandler<DeleteOrg, OrgDeleted> handler;

    @Inject
    OrgsRepository repo;
    private ObjectId createdOrgId;

    @BeforeEach
    void setUp() {
        var org = new Org();
        org.name = "NaUKMA";
        repo.insert(org);
        createdOrgId = org.id;
    }

    @Test
    void testSuccessFullyDeletion() {
        var command = new DeleteOrg();
        command.setOrgId(this.createdOrgId);


        var result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        var recordStillExists = this.repo.findById(createdOrgId);
        Assertions.assertEquals(null, recordStillExists);
    }
}
