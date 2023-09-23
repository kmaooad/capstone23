package edu.kmaooad.capstone23.relations.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.relations.commands.SetRelation;
import edu.kmaooad.capstone23.relations.events.RelationSet;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class SetRelationHandlerTests {

    @Inject
    CommandHandler<SetRelation, RelationSet> handler;

    @Test
    @DisplayName("Basic Relation Handling")
    void testSuccessfulRelationHandling() {
        var command = createDefaultCommand();
        var result = handler.handle(command);
        assertTrue(result.isSuccess());
        assertNotNull(result.getValue());
        assertNotNull(result.getValue().id());
    }

    @Test
    @DisplayName("Invalid Relation Input")
    void testInvalidRelationInput() {
        var command = createDefaultCommand();
        command.collectionName1 = ""; // invalid coll. name

        var result = handler.handle(command);
        assertFalse(result.isSuccess());
    }

    private SetRelation createDefaultCommand() {
        var command = new SetRelation();
        command.collectionName1 = "SomeCollection";
        command.collectionName2 = "AnotherCollection";
        command.objectToConnectId1 = new ObjectId("5f7e47fc8e1f7112d73c92a1");
        command.objectToConnectId2 = new ObjectId("1a4cd132b123a1aa3bc2d142");

        return command;
    }
}
