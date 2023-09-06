package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateTopic;
import edu.kmaooad.capstone23.competences.commands.UpdateTopic;
import edu.kmaooad.capstone23.competences.events.TopicCreated;
import edu.kmaooad.capstone23.competences.events.TopicUpdated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateTopicHandlerTest {

    @Inject
    CommandHandler<UpdateTopic, TopicUpdated> updateHandler;

    @Inject
    CommandHandler<CreateTopic, TopicCreated> createHandler;

    @Test
    void testSuccessfulHandling() {
        var command = new CreateTopic();
        command.setTopicName("food");

        Result<TopicCreated> result = createHandler.handle(command);

        Result<TopicUpdated> result2 = changeTopic(result.getValue().getTopic());

        Assertions.assertTrue(result2.isSuccess());
        Assertions.assertNotNull(result2.getValue());
        Assertions.assertNotNull(result2.getValue().getTopic());
    }


    Result<TopicUpdated> changeTopic(ObjectId id) {
        var command = new UpdateTopic();
        command.setTopicName("drink");
        command.setId(id);


        Result<TopicUpdated> result = updateHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertNotNull(result.getValue().getTopic());
        return result;
    }
}
