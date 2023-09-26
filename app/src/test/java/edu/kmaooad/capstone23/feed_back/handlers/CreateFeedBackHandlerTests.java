package edu.kmaooad.capstone23.feed_back.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.feed_back.commands.CreateFeedBack;
import edu.kmaooad.capstone23.feed_back.events.FeedBackCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


@QuarkusTest
public class CreateFeedBackHandlerTests {

    @Inject
    CommandHandler<CreateFeedBack, FeedBackCreated> handler;

    @Test
    void testSuccessfulHandling() {
        CreateFeedBack command = new CreateFeedBack();
        command.setText("Test of review result.");
        command.setTopic("Review Result");

        Result<FeedBackCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getId().isEmpty());
    }

    @Test
    void testNotSuccessfulHandling() {
        CreateFeedBack command = new CreateFeedBack();
        command.setText("Test of review result.");

        Result<FeedBackCreated> result = handler.handle(command);
        Assertions.assertFalse(result.isSuccess());
    }

}
