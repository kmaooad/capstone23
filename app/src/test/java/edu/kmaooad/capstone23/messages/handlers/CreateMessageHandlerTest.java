package edu.kmaooad.capstone23.messages.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.massages.commands.CreateMessage;
import edu.kmaooad.capstone23.massages.events.MessageCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
    public class CreateMessageHandlerTest {
    @Inject
    CommandHandler<CreateMessage, MessageCreated> handler;

    @Test
    void testSuccessfulHandlingCVCreatingBysms() {
        CreateMessage command = new CreateMessage("CV is created", "cvCreated", "sms");

        Result<MessageCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
    }

    @Test
    void testSuccessfulHandlingCVCreatingByEmail() {
        CreateMessage command = new CreateMessage("CV is created", "cvCreated", "email");

        Result<MessageCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
    }
}
