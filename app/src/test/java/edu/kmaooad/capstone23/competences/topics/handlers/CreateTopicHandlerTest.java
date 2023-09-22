package edu.kmaooad.capstone23.competences.topics.handlers;

import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateTopic;
import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.dal.TopicRepository;
import edu.kmaooad.capstone23.competences.events.TopicCreated;
import edu.kmaooad.capstone23.competences.handlers.CreateTopicHandler;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@QuarkusTest
public class CreateTopicHandlerTest {

    @Inject
    CreateTopicHandler handler;

    @Inject
    TopicRepository topicRepository;

    @Test
    @DisplayName("Create Topic: Basic")
    void testSuccessfulHandling() {
        CreateTopic command = new CreateTopic();
        command.name = "New Topic";

        Result<TopicCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getId().isEmpty());

        Optional<Topic> createdTopicOptional = topicRepository.findById(result.getValue().getId());
        Assertions.assertTrue(createdTopicOptional.isPresent());
        Topic createdTopic = createdTopicOptional.get();
        Assertions.assertEquals("New Topic", createdTopic.name);
    }

    @Test
    @DisplayName("Create Topic: Basic with parent")
    void testCreateWithParentTopic() {
        Topic parentTopic = new Topic();
        parentTopic.name = "Parent Topic";
        topicRepository.insert(parentTopic);

        CreateTopic command = new CreateTopic();
        command.name = "Child Topic";
        command.parentId = parentTopic.id.toHexString();

        Result<TopicCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getId().isEmpty());

        Optional<Topic> createdTopicOptional = topicRepository.findById(result.getValue().getId());
        Assertions.assertTrue(createdTopicOptional.isPresent());
        Topic createdTopic = createdTopicOptional.get();
        Assertions.assertEquals("Child Topic", createdTopic.name);
        Assertions.assertEquals(parentTopic.id.toHexString(), createdTopic.parentId);
    }

    @Test
    @DisplayName("Create Topic: Basic with not exist parent")
    void testCreateWithNonExistentParentTopic() {
        CreateTopic command = new CreateTopic();
        command.name = "Invalid Child Topic";
        command.parentId = "nonExistentParentId";

        Result<TopicCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.EXCEPTION, result.getErrorCode());
        Assertions.assertEquals("Parent topic not found", result.getMessage());
    }
}
