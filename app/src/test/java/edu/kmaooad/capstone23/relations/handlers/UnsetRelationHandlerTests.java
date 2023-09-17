package edu.kmaooad.capstone23.relations.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.relations.commands.UnsetRelation;
import edu.kmaooad.capstone23.relations.events.RelationUnset;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class UnsetRelationHandlerTests {

    @Inject
    CommandHandler<UnsetRelation, RelationUnset> handler;

    @Test
    @DisplayName("Successfully Unset Relation")
    void testSuccessfulRelationUnset() {
        var command = createDefaultCommand();
        var result = handler.handle(command);
        assertTrue(result.isSuccess());
        assertNotNull(result.getValue());
        assertNotNull(result.getValue().id());
    }

    @Test
    @DisplayName("Unsetting Non-existent Relation")
    void testNonexistentRelationUnset() {
        var command = createDefaultCommand();
        command = new UnsetRelation(new ObjectId("5f7e47fc8e1f7112d73c92a0"), "NonExistentCollection", "AnotherNonExistentCollection");

        var result = handler.handle(command);
        assertFalse(result.isSuccess());
    }

    private UnsetRelation createDefaultCommand() {
        return new UnsetRelation(
                new ObjectId("5f7e47fc8e1f7112d73c92a1"),
                "SomeCollection",
                "AnotherCollection"
        );
    }
}
