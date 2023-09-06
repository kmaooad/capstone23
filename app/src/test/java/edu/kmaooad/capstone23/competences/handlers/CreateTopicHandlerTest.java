package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateTopic;
import edu.kmaooad.capstone23.competences.events.TopicCreated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateTopicHandlerTest {

    @Inject
    CommandHandler<CreateTopic, TopicCreated> handler;

    @Test
    void testSuccessfulHandling() {
        var command = new CreateTopic();
        command.setTopicName("food");

        Result<TopicCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertNotNull(result.getValue().getTopic());
    }

    @Test
    void testSuccessfulRelationHandling() {
        var command = new CreateTopic();
        command.setTopicName("food");

        Result<TopicCreated> result = handler.handle(command);

        Assertions.assertNotNull(result.getValue().getTopic());

        var command2 = new CreateTopic();
        command2.setTopicName("fruit");
        command2.setParentTopic(result.getValue().getTopic());

        Result<TopicCreated> result2 = handler.handle(command2);

        Assertions.assertTrue(result2.isSuccess());
        Assertions.assertNotNull(result2.getValue());
        Assertions.assertNotNull(result2.getValue().getTopic());
    }
}
