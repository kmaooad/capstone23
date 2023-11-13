package edu.kmaooad.capstone23.relations.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.relations.commands.UnsetRelation;
import edu.kmaooad.capstone23.relations.dal.Relation;
import edu.kmaooad.capstone23.relations.dal.RelationRepository;
import edu.kmaooad.capstone23.relations.events.RelationUnset;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class UnsetRelationHandlerTests {

    @Inject
    CommandHandler<UnsetRelation, RelationUnset> handler;

    @Inject
    RelationRepository repository;

    @Test
    @DisplayName("Successfully Unset Relation")
    void testSuccessfulRelationUnset() {
        ObjectId relationToUnsetID = createAndPersistDefaultRelation();
        UnsetRelation command = new UnsetRelation(relationToUnsetID, "courses", "projects");

        Result<RelationUnset> result = handler.handle(command);

        assertAll("Valid Relation Unset",
                () -> assertTrue(result.isSuccess(), "Result should be successful"),
                () -> assertNotNull(result.getValue(), "Result value should not be null"),
                () -> assertEquals(relationToUnsetID, result.getValue().id(), "Result value id should match the unset relation id")
        );
    }

    @Test
    @DisplayName("Unsetting Non-existent Relation")
    void testNonexistentRelationUnset() {
        ObjectId fakeId = new ObjectId("5f7e47fc8e1f7112d73c92a0");
        UnsetRelation command = new UnsetRelation(fakeId, "NonExistentCollection", "AnotherNonExistentCollection");

        Result<RelationUnset> result = handler.handle(command);

        assertFalse(result.isSuccess(), "Result should not be successful for non-existent relation");
    }

    private ObjectId createAndPersistDefaultRelation() {
        Relation relation = new Relation(new ObjectId(), new ObjectId());
        Optional<Relation> created = repository.createRelation("courses", "projects", relation);

        assertTrue(created.isPresent(), "Created relation should be present");

        return created.get().getId();
    }
}
