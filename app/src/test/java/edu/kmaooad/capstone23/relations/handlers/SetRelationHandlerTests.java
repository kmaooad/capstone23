package edu.kmaooad.capstone23.relations.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
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
        SetRelation command = createDefaultCommand();
        Result<RelationSet> result = handler.handle(command);

        assertAll("Valid Relation Handling",
                () -> assertTrue(result.isSuccess(), "Result should be successful"),
                () -> assertNotNull(result.getValue(), "Result value should not be null"),
                () -> assertNotNull(result.getValue().id(), "Result value id should not be null")
        );
    }

    @Test
    @DisplayName("Invalid Relation Input")
    void testInvalidRelationInput() {
        SetRelation command = createDefaultCommand();
        command.collectionName1 = ""; // invalid collection name

        Result<RelationSet> result = handler.handle(command);

        assertFalse(result.isSuccess(), "Result should not be successful for invalid input");
    }

    private SetRelation createDefaultCommand() {
        SetRelation command = new SetRelation();
        command.collectionName1= "SomeCollection";
        command.collectionName2="AnotherCollection";
        command.objectToConnectId1=new ObjectId("5f7e47fc8e1f7112d73c92a1");
        command.objectToConnectId2=new ObjectId("1a4cd132b123a1aa3bc2d142");
        return command;
    }

}
