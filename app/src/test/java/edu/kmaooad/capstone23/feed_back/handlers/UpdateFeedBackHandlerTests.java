package edu.kmaooad.capstone23.feed_back.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.feed_back.commands.UpdateFeedBack;
import edu.kmaooad.capstone23.feed_back.dal.FeedBack;
import edu.kmaooad.capstone23.feed_back.dal.FeedBackRepository;
import edu.kmaooad.capstone23.feed_back.events.FeedBackUpdated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;


@QuarkusTest
public class UpdateFeedBackHandlerTests {

    @Inject
    CommandHandler<UpdateFeedBack, FeedBackUpdated> handler;
    @Inject
    FeedBackRepository repository;
    private FeedBack feedBack;

    @BeforeEach
    void setUp() {
        feedBack = new FeedBack();
        feedBack.topic = "Review Result";
        feedBack.text = "Test of review result.";
        feedBack.date = new Date();
        repository.insert(feedBack);
    }

    @AfterEach
    void after() {
        repository.delete(feedBack);
    }

    @Test
    void testSuccessfulHandling() {
        UpdateFeedBack command = new UpdateFeedBack();
        command.setFeedBackId(feedBack.id.toString());
        command.setText("Test of review result. New.");
        command.setTopic(feedBack.topic);

        Result<FeedBackUpdated> result = handler.handle(command);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getFeedBackId().isEmpty());
        Assertions.assertEquals("Test of review result. New.", result.getValue().getFeedBackText());
    }

    @Test
    void testNonExistedFeedBack() {
        UpdateFeedBack command = new UpdateFeedBack();
        var id = new ObjectId().toString();
        command.setFeedBackId(id);
        command.setText("Test of review result. New.");
        command.setTopic(feedBack.topic);

        Result<FeedBackUpdated> result = handler.handle(command);
        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
        Assertions.assertEquals(String.format("Feedback with id: %s does not exist", id), result.getMessage());
    }

    @Test
    void testNameValidation() {
        UpdateFeedBack command = new UpdateFeedBack();
        command.setFeedBackId(feedBack.id.toString());
        command.setTopic(feedBack.topic);

        Result<FeedBackUpdated> result = handler.handle(command);
        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
    }

    @Test
    void testTopicLengthIsTooShort() {
        UpdateFeedBack command = new UpdateFeedBack();
        command.setFeedBackId(feedBack.id.toString());
        command.setTopic("Abc");

        Result<FeedBackUpdated> result = handler.handle(command);
        Assertions.assertFalse(result.isSuccess());
    }

}
