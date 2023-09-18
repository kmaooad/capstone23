package edu.kmaooad.capstone23.cvs.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import edu.kmaooad.capstone23.cvs.dal.CV;
import java.time.LocalDateTime;
import edu.kmaooad.capstone23.common.*;
import edu.kmaooad.capstone23.cvs.commands.CreateCV;
import edu.kmaooad.capstone23.cvs.events.CVCreated;
import edu.kmaooad.capstone23.cvs.commands.ReadCV;
import edu.kmaooad.capstone23.cvs.events.CVRead;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
@QuarkusTest
public class ReadCVHandlerTest {

    @Inject
    CommandHandler<CreateCV, CVCreated> createHandler;

    @Inject
    CommandHandler<ReadCV, CVRead> readHandler;

    @Inject
    CVRepository cvRepository;

    @Test
    @DisplayName("Read Cvs: with status")
    void testHandleWithStatus() {
        cvRepository.deleteAll();

        CreateCV createCommand1 = new CreateCV();
        createCommand1.setDateTimeCreated(LocalDateTime.now());
        createCommand1.setTextInfo("some info about a student");
        createCommand1.setStatus(CV.Status.CLOSED);
        createCommand1.setVisibility(CV.Visibility.VISIBLE);
        Result<CVCreated> resultreate1 = createHandler.handle(createCommand1);

        CreateCV createCommand2 = new CreateCV();
        createCommand2.setDateTimeCreated(LocalDateTime.now());
        createCommand2.setTextInfo("new student");
        createCommand2.setStatus(CV.Status.OPEN);
        createCommand2.setVisibility(CV.Visibility.VISIBLE);
        Result<CVCreated> resultreate2 = createHandler.handle(createCommand2);

        CreateCV createCommand3 = new CreateCV();
        createCommand3.setDateTimeCreated(LocalDateTime.now());
        createCommand3.setTextInfo("here info about student");
        createCommand3.setStatus(CV.Status.OPEN);
        createCommand3.setVisibility(CV.Visibility.VISIBLE);
        Result<CVCreated> resultreate3 = createHandler.handle(createCommand3);

        ReadCV readCommand = new ReadCV();
        readCommand.setStatus(CV.Status.OPEN);
        Result<CVRead> result = readHandler.handle(readCommand);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(2,result.getValue().getCvs().size());
    }

    @Test
    @DisplayName("Read Cvs: without status")
    void testHandleWithoutStatus() {
        cvRepository.deleteAll();

        CreateCV createCommand1 = new CreateCV();
        createCommand1.setDateTimeCreated(LocalDateTime.now());
        createCommand1.setTextInfo("some info about a student");
        createCommand1.setStatus(CV.Status.CLOSED);
        createCommand1.setVisibility(CV.Visibility.VISIBLE);
        Result<CVCreated> resultreate1 = createHandler.handle(createCommand1);

        CreateCV createCommand2 = new CreateCV();
        createCommand2.setDateTimeCreated(LocalDateTime.now());
        createCommand2.setTextInfo("new student");
        createCommand2.setStatus(CV.Status.OPEN);
        createCommand2.setVisibility(CV.Visibility.VISIBLE);
        Result<CVCreated> resultreate2 = createHandler.handle(createCommand2);

        CreateCV createCommand3 = new CreateCV();
        createCommand3.setDateTimeCreated(LocalDateTime.now());
        createCommand3.setTextInfo("here info about student");
        createCommand3.setStatus(CV.Status.OPEN);
        createCommand3.setVisibility(CV.Visibility.VISIBLE);
        Result<CVCreated> resultreate3 = createHandler.handle(createCommand3);

        ReadCV readCommand = new ReadCV();
        Result<CVRead> result = readHandler.handle(readCommand);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(3,result.getValue().getCvs().size());
    }

}
