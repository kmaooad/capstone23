package edu.kmaooad.capstone23.competences.topics.handlers;

import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.DeleteTopic;
import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.dal.TopicRepository;
import edu.kmaooad.capstone23.competences.events.TopicDeleted;
import edu.kmaooad.capstone23.competences.handlers.DeleteTopicHandler;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;

import java.util.Optional;

@QuarkusTest
public class DeleteTopicHandlerTest {
    @BeforeAll
    static void deleteAllData() {
        TopicRepository repository = new TopicRepository();
        repository.deleteAll();
    }
    private ObjectId idToDelete;
    private ObjectId parentId;
    private ObjectId idWithParent;

    @Inject
    DeleteTopicHandler deleteTopicHandler;

    @Inject
    TopicRepository topicRepository;

    @BeforeEach
    void setUp() {
        Topic topic = new Topic();
        topic.name = "Topic w/o parent";
        topic.parentId = null;
        topicRepository.insert(topic);
        idToDelete = topic.id;

        Topic parent = new Topic();
        parent.name = "Parent Topic";
        parent.parentId = null;
        topicRepository.insert(parent);
        parentId = parent.id;

        Topic topicWithParent = new Topic();
        topicWithParent.name = "Topic With Parent";
        topicWithParent.parentId = parentId.toString();
        topicRepository.insert(topicWithParent);
        idWithParent = topicWithParent.id;
    }

    @Test
    @DisplayName("Delete Topic: Basic")
    public void testDeleteTopic() {
        DeleteTopic command = new DeleteTopic();
        command.setId(idToDelete.toString());

        Result<TopicDeleted> result = deleteTopicHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getId().isEmpty());

        Optional<Topic> deletedTopicOptional = topicRepository.findById(idToDelete.toString());
        Assertions.assertFalse(deletedTopicOptional.isPresent());
    }

    @Test
    @DisplayName("Delete Topic: With Children")
    public void testDeleteTopicWithChildren() {
        DeleteTopic command = new DeleteTopic();
        command.setId(parentId.toString());

        Result<TopicDeleted> result = deleteTopicHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
        Assertions.assertEquals("Topic has children. Delete the children first.", result.getMessage());

        Optional<Topic> parentTopicOptional = topicRepository.findById(parentId.toString());
        Assertions.assertTrue(parentTopicOptional.isPresent());
    }

    @Test
    @DisplayName("Delete Topic: Child")
    public void testDeleteTopicChild() {
        DeleteTopic command = new DeleteTopic();
        command.setId(idWithParent.toString());

        Result<TopicDeleted> result = deleteTopicHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getId().isEmpty());

        Optional<Topic> deletedTopicOptional = topicRepository.findById(idWithParent.toString());
        Assertions.assertFalse(deletedTopicOptional.isPresent());
    }

}
