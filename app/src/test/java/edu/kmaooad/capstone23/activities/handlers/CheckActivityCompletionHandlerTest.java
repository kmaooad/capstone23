package edu.kmaooad.capstone23.activities.handlers;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.kmaooad.capstone23.activities.commands.CheckActivityCompletion;
import edu.kmaooad.capstone23.activities.dal.Activity;
import edu.kmaooad.capstone23.activities.events.ActivityCompletionChecked;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class CheckActivityCompletionHandlerTest {
    @Inject
    CommandHandler<CheckActivityCompletion, ActivityCompletionChecked> handler;

    @Test
    @DisplayName("Check Activity Completion Handler Test")
    void testSuccessfulCreate() {
        Activity activity = new Activity();
        activity.name = "fooBar";
        activity.inProgress = false;
        activity.completed = false;
        final Date startDate = new Date();
        final Date finishDate = new Date(startDate.getTime() + 5000);
        final Date actualDate = new Date(startDate.getTime() + 10000);
        activity.startDate = startDate;
        activity.finishDate = finishDate;
        CheckActivityCompletion command = new CheckActivityCompletion();
        command.setActivity(activity);
        command.setActualDate(actualDate);

        Result<ActivityCompletionChecked> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertFalse(result.getValue().getInProgress());
        Assertions.assertTrue(result.getValue().getCompleted());
    }

    @Test
    @DisplayName("Check Activity Completion BeforeStartDate rHandler Test")
    void testSuccessfulCreateBeforeStartDate() {
        Activity activity = new Activity();
        activity.name = "fooBarBeforeStart";
        activity.inProgress = false;
        activity.completed = false;
        final Date startDate = new Date();
        final Date finishDate = new Date(startDate.getTime() + 5000);
        final Date actualDate = new Date(startDate.getTime() - 10000);
        activity.startDate = startDate;
        activity.finishDate = finishDate;
        CheckActivityCompletion command = new CheckActivityCompletion();
        command.setActivity(activity);
        command.setActualDate(actualDate);

        Result<ActivityCompletionChecked> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertFalse(result.getValue().getInProgress());
        Assertions.assertFalse(result.getValue().getCompleted());
    }

    @Test
    @DisplayName("Check Activity Completion In progress rHandler Test")
    void testSuccessfulCreateInProgress() {

        String name = "fooBarInProgress";
        final Date startDate = new Date();
        final Date finishDate = new Date(startDate.getTime() + 5000);
        final Date actualDate = new Date(startDate.getTime() + 1000);
        Activity activity = new Activity(name, startDate, finishDate);

        CheckActivityCompletion command = new CheckActivityCompletion();
        command.setActivity(activity);
        command.setActualDate(actualDate);

        Result<ActivityCompletionChecked> result = handler.handle(command);
        Assertions.assertNotNull(activity.isCompleted());
        Assertions.assertNotNull(activity.getName());
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertTrue(result.getValue().getInProgress());
        Assertions.assertFalse(result.getValue().getCompleted());
    }
}
