package edu.kmaooad.capstone23.cvs.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import edu.kmaooad.capstone23.cvs.dal.CV;
import java.time.LocalDateTime;
import java.util.HashSet;

import edu.kmaooad.capstone23.common.*;
import edu.kmaooad.capstone23.cvs.commands.CreateCV;
import edu.kmaooad.capstone23.cvs.events.CVCreated;
import edu.kmaooad.capstone23.cvs.commands.UpdateCV;
import edu.kmaooad.capstone23.cvs.events.CVUpdated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.bson.types.ObjectId;
@QuarkusTest
public class UpdateCVHandlerTest {

    @Inject
    CommandHandler<CreateCV, CVCreated> createHandler;

    @Inject
    CommandHandler<UpdateCV, CVUpdated> updateHandler;

    ObjectId getCreateCvId(){
        CreateCV command = new CreateCV();
        command.setDateTimeCreated(LocalDateTime.now());
        command.setTextInfo("some info about a student");
        command.setStatus(CV.Status.OPEN);
        command.setVisibility(CV.Visibility.VISIBLE);

        Result<CVCreated> result = createHandler.handle(command);
        return result.getValue().getCVId();
    }

    @Test
    @DisplayName("Update Cvs: successful handling")
    void testSuccessfulHandling() {
        UpdateCV command = new UpdateCV();
        command.setCvId(getCreateCvId());
        command.setStatus(CV.Status.CLOSED);
        command.setVisibility(CV.Visibility.HIDDEN);
        command.setTextInfo("new info");
        command.setSkills(new HashSet<>());

        Result<CVUpdated> result = updateHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
    }

    @Test
    @DisplayName("Update Cvs: update only text info")
    void testSuccessfulOneChangeHandling() {
        UpdateCV command = new UpdateCV();
        command.setCvId(getCreateCvId());
        command.setTextInfo("new info");

        Result<CVUpdated> result = updateHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
    }

}
