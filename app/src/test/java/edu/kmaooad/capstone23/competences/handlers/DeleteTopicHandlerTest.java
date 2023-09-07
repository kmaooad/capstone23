package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.DeleteTopic;
import edu.kmaooad.capstone23.competences.events.TopicDeleted;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeleteTopicHandlerTest {

    @Inject
    CommandHandler<DeleteTopic, TopicDeleted> handler;

    @Test
    void testSuccessfulHandling() {
        var command = new DeleteTopic();
        command.setId(new ObjectId("64f83a309682b0261c7bd24e"));

        Result<TopicDeleted> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertNotNull(result.getValue().getTopic());
    }

    // @Test
    // void testSuccessfulRelationHandling() {
    //     var command = new DeleteTopic();
    //     command.setTopicName("food");

    //     Result<TopicDeleted> result = handler.handle(command);

    //     Assertions.assertNotNull(result.getValue().getTopic());

    //     var command2 = new DeleteTopic();
    //     command2.setTopicName("fruit");
    //     command2.setParentTopic(result.getValue().getTopic());

    //     Result<TopicDeleted> result2 = handler.handle(command2);

    //     Assertions.assertTrue(result2.isSuccess());
    //     Assertions.assertNotNull(result2.getValue());
    //     Assertions.assertNotNull(result2.getValue().getTopic());
    // }
}
