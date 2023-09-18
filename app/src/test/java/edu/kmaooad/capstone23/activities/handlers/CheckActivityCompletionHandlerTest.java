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
import jakarta.inject.Inject;

public class CheckActivityCompletionHandlerTest {
    @Inject
    CommandHandler<CheckActivityCompletion, ActivityCompletionChecked> handler;

    @Test
    @DisplayName("Handle Create Course command")
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
        Assertions.assertNotNull(result.getValue().getId());
        Assertions.assertFalse(result.getValue().getInProgress());
        Assertions.assertTrue(result.getValue().getCompleted());
    }
}
