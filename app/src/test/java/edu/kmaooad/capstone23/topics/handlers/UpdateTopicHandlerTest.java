package edu.kmaooad.capstone23.topics.handlers;

import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.UpdateTopic;
import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.dal.TopicRepository;
import edu.kmaooad.capstone23.competences.events.TopicUpdated;
import edu.kmaooad.capstone23.competences.handlers.UpdateTopicHandler;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@QuarkusTest
public class UpdateTopicHandlerTest {

    private ObjectId idToUpdate;
    private ObjectId parentId;
    private ObjectId idWithParent;

    @Inject
    UpdateTopicHandler updateTopicHandler;

    @Inject
    TopicRepository topicRepository;

    @BeforeEach
    void setUp() {
        Topic topic = new Topic();
        topic.name = "Initial Topic";
        topic.parentId = null;
        topicRepository.insert(topic);
        idToUpdate = topic.id;

        Topic parent = new Topic();
        parent.name = "Parent Topic";
        parent.parentId = null;
        topicRepository.insert(parent);
        parentId = parent.id;

        Topic topicWithParent = new Topic();
        topicWithParent.name = "New Topic With Parent";
        topicWithParent.parentId = parentId.toString();
        topicRepository.insert(topicWithParent);
        idWithParent = topicWithParent.id;
    }

    @Test
    void testSuccessfulBasicHandling() {
        UpdateTopic command = new UpdateTopic();
        command.setId(idToUpdate.toString());
        command.setTopicName("Updated Topic");

        Result<TopicUpdated> result = updateTopicHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getId().isEmpty());
        Assertions.assertFalse(result.getValue().getName().isEmpty());

        Optional<Topic> updatedTopicOptional = topicRepository.findById(idToUpdate.toString());
        Assertions.assertTrue(updatedTopicOptional.isPresent());

        Topic updatedTopic = updatedTopicOptional.get();
        Assertions.assertEquals("Updated Topic", updatedTopic.name);
    }

    @Test
    void testSuccessfulHandlingAddParent() {
        UpdateTopic command = new UpdateTopic();
        command.setId(idToUpdate.toString());
        command.setTopicName("Updated Topic");
        command.setParentId(parentId.toString());

        Result<TopicUpdated> result = updateTopicHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getId().isEmpty());
        Assertions.assertFalse(result.getValue().getName().isEmpty());
        Assertions.assertFalse(result.getValue().getParentId().isEmpty());

        Optional<Topic> updatedTopicOptional = topicRepository.findById(idToUpdate.toString());
        Assertions.assertTrue(updatedTopicOptional.isPresent());

        Topic updatedTopic = updatedTopicOptional.get();
        Assertions.assertEquals("Updated Topic", updatedTopic.name);
        Assertions.assertEquals(parentId.toString(), updatedTopic.parentId);
    }

    @Test
    void testSuccessfulHandlingRemoveParent() {
        UpdateTopic command = new UpdateTopic();
        command.setId(idWithParent.toString());
        command.setTopicName("Updated Topic");
        command.setParentId(null);

        Result<TopicUpdated> result = updateTopicHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getId().isEmpty());
        Assertions.assertFalse(result.getValue().getName().isEmpty());
        Assertions.assertNull(result.getValue().getParentId());

        Optional<Topic> updatedTopicOptional = topicRepository.findById(idWithParent.toString());
        Assertions.assertTrue(updatedTopicOptional.isPresent());

        Topic updatedTopic = updatedTopicOptional.get();
        Assertions.assertEquals("Updated Topic", updatedTopic.name);
        Assertions.assertNull(updatedTopic.parentId);
    }

    @Test
    void testNonExistentTopic() {
        UpdateTopic command = new UpdateTopic();
        command.setId("nonExistentId");
        command.setTopicName("Updated Topic");

        Result<TopicUpdated> result = updateTopicHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
        Assertions.assertEquals("Topic with this ID does not exist", result.getMessage());
    }

    @Test
    void testNonExistentParentTopic() {
        UpdateTopic command = new UpdateTopic();
        command.setId(idToUpdate.toString());
        command.setTopicName("Updated Topic");
        command.setParentId("nonExistentParentId");

        Result<TopicUpdated> result = updateTopicHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
        Assertions.assertEquals("Parent Topic with this ID does not exist", result.getMessage());
    }

    @Test
    void testInvalidTopicName() {
        UpdateTopic command = new UpdateTopic();
        command.setId(idToUpdate.toString());
        command.setTopicName("Updated Wrong @123");

        Result<TopicUpdated> result = updateTopicHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
        Assertions.assertEquals("must match \"^[a-zA-Z0-9 ]*$\"", result.getMessage());
    }
}
