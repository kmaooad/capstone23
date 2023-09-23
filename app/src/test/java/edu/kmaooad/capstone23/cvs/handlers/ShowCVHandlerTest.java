package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.CreateCV;
import edu.kmaooad.capstone23.cvs.commands.ShowCV;
import edu.kmaooad.capstone23.cvs.commands.UpdateCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.events.CVCreated;
import edu.kmaooad.capstone23.cvs.events.CVUpdated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

@QuarkusTest
class ShowCVHandlerTest {
    @Inject
    CommandHandler<CreateCV, CVCreated> createHandler;

    @Inject
    CommandHandler<ShowCV, CVUpdated> showHandler;

    ObjectId getCreateCvId() {
        CreateCV command = new CreateCV();
        command.setDateTimeCreated(LocalDateTime.now());
        command.setTextInfo("some info about a student");
        command.setStatus(CV.Status.OPEN);
        command.setVisibility(CV.Visibility.VISIBLE);

        Result<CVCreated> result = createHandler.handle(command);
        return result.getValue().getCVId();
    }

    @Test
    @DisplayName("Show CV: successful handling")
    void testSuccessfulHandling() {
        ShowCV command = new ShowCV();
        command.setCvId(getCreateCvId());

        Result<CVUpdated> result = showHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
    }

    @Test
    @DisplayName("Show CV: invalid objectID")
    void testUnsuccessfulOneChangeHandling() {
        ShowCV command = new ShowCV();
        command.setCvId(new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa"));

        Result<CVUpdated> result = showHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertNull(result.getValue());
    }
}