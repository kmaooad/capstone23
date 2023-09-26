package edu.kmaooad.capstone23.feed_back.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.DeleteSkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import edu.kmaooad.capstone23.competences.events.SkillSetDeleted;
import edu.kmaooad.capstone23.feed_back.commands.DeleteFeedBack;
import edu.kmaooad.capstone23.feed_back.dal.FeedBack;
import edu.kmaooad.capstone23.feed_back.dal.FeedBackRepository;
import edu.kmaooad.capstone23.feed_back.events.FeedBackDeleted;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

@QuarkusTest
public class DeleteFeedBackHandlerTest {

    @Inject
    CommandHandler<DeleteFeedBack, FeedBackDeleted> handler;

    @Inject
    private FeedBackRepository repository;

    private ObjectId idToDelete;

    @BeforeEach
    void setUp() {
        FeedBack feedBack = new FeedBack();
        feedBack.topic = "Decorators";
        feedBack.text = "Some text";
        feedBack.date = new Date();
        repository.insert(feedBack);
        idToDelete = feedBack.id;
    }

    @Test
    void testSuccessfulHandling() {
        var command = new DeleteFeedBack();
        command.setId(idToDelete);

        Result<FeedBackDeleted> result = handler.handle(command);

        Assertions.assertNull(repository.findById(idToDelete));
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertNotNull(result.getValue().getFeedBack());
    }

    @Test
    void testDeletingNonExistingFeedBack() {
        var command = new DeleteFeedBack();
        command.setId(new ObjectId());

        Result<FeedBackDeleted> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

}
