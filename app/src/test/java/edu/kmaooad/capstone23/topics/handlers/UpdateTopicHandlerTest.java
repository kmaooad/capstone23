package edu.kmaooad.capstone23.topics.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.UpdateTopic;
import edu.kmaooad.capstone23.competences.events.TopicUpdated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateTopicHandlerTest {
    @Inject
    CommandHandler<UpdateTopic, TopicUpdated> handler;

    @Test
    void testSuccessfulHandling() {
        UpdateTopic command = new UpdateTopic();
        command.setId("2");
        command.setTopicName("NaUKMA");
        command.setParentId("1");

        Result<TopicUpdated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getTopicId().isEmpty());
    }

    @Test
    void testNameValidation() {
        UpdateTopic command = new UpdateTopic();
        command.setTopicName("NaUKMA_2023");

        Result<TopicUpdated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }
}
