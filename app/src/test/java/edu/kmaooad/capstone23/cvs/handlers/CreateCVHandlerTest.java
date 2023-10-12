package edu.kmaooad.capstone23.cvs.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import edu.kmaooad.capstone23.cvs.dal.CV;
import java.time.LocalDateTime;
import edu.kmaooad.capstone23.common.*;
import edu.kmaooad.capstone23.cvs.commands.CreateCV;
import edu.kmaooad.capstone23.cvs.events.CVCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
@QuarkusTest
public class CreateCVHandlerTest {

    @Inject
    CommandHandler<CreateCV, CVCreated> handler;

    @Test
    @DisplayName("Create Cvs: successful handling")
    void testSuccessfulHandling() {
        CreateCV command = new CreateCV();
        command.setDateTimeCreated(LocalDateTime.now());
        command.setTextInfo("some info about a student");
        command.setStatus(CV.Status.OPEN);
        command.setVisibility(CV.Visibility.VISIBLE);

        Result<CVCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertNotNull(result.getValue().getCVId());
    }

    @Test
    @DisplayName("Create Cvs: DateTimeCreated null")
    void testNotSuccessfulHandling() {
        CreateCV command = new CreateCV();
        //DateTimeCreated null
        command.setTextInfo("some info about a student");
        command.setStatus(CV.Status.OPEN);
        command.setVisibility(CV.Visibility.VISIBLE);

        Result<CVCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Create Cvs: wrong DateTimeCreated")
    void testWrongDateTimeCreatedHandling() {
        CreateCV command = new CreateCV();
        command.setDateTimeCreated(LocalDateTime.now().plusYears(1));
        command.setTextInfo("some info about a student");
        command.setStatus(CV.Status.OPEN);
        command.setVisibility(CV.Visibility.VISIBLE);

        Result<CVCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Create Cvs: text info is blank")
    void testTextInfoIsBlankHandling() {
        CreateCV command = new CreateCV();
        command.setDateTimeCreated(LocalDateTime.now());
        command.setTextInfo(" ");
        command.setStatus(CV.Status.OPEN);
        command.setVisibility(CV.Visibility.VISIBLE);

        Result<CVCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Create Cvs: visibility is null")
    void testVisibilityIsNullHandling() {
        CreateCV command = new CreateCV();
        command.setDateTimeCreated(LocalDateTime.now());
        command.setTextInfo("some info about a student");
        command.setStatus(CV.Status.OPEN);

        Result<CVCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }
}
