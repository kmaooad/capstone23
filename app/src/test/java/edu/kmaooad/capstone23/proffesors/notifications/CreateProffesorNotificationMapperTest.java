package edu.kmaooad.capstone23.proffesors.notifications;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.sms.SMS;
import edu.kmaooad.capstone23.proffesors.commands.CreateProffesor;
import edu.kmaooad.capstone23.proffesors.events.ProffesorCreated;
import edu.kmaooad.capstone23.proffesors.notifications.create.CreateProffesorNotificationMapper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
class CreateProffesorNotificationMapperTest {
    @Inject
    CreateProffesorNotificationMapper mapper;
    @Inject
    CommandHandler<CreateProffesor, ProffesorCreated> handler;

    @Test
    @DisplayName("Create Proffesor: correct notification mapping")
    void testCorrectMapping() {
        CreateProffesor command = new CreateProffesor();
        command.setName("Masha");
        command.setEmail("post@gmail.com");
        command.setLastName("Shevchenko");


        Result<ProffesorCreated> result = handler.handle(command);
        String id = result.getValue().getId().toString();


        ProffesorCreated event = result.getValue();
        SMS actual = mapper.objectToSms(event);
        String expectedBody = "New professor with id " + id + " added.";
        Assertions.assertEquals("New professor", actual.title());
        Assertions.assertEquals(expectedBody, actual.body());
        Assertions.assertEquals("test@gmail.com", actual.recipient());
    }

}