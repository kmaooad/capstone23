package edu.kmaooad.capstone23.proffesors.handlers;




import edu.kmaooad.capstone23.proffesors.commands.CreateProffesor;
import edu.kmaooad.capstone23.proffesors.dal.Proffesor;
import edu.kmaooad.capstone23.proffesors.events.ProffesorCreated;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.time.LocalDateTime;
import edu.kmaooad.capstone23.common.*;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
    @QuarkusTest
    public class CreateProffesorHandlerTest {

        @Inject
        CommandHandler<CreateProffesor, ProffesorCreated> handler;

        @Test
        @DisplayName("Create Cvs: successful handling")
        void testSuccessfulHandling() {
            CreateProffesor command = new CreateProffesor();
            command.setName("Masha");
            command.setEmail("post@gmail.com");
            command.setLastName("Shevchenko");


            Result<ProffesorCreated> result = handler.handle(command);

            Assertions.assertTrue(result.isSuccess());
            Assertions.assertNotNull(result.getValue());
            Assertions.assertNotNull(result.getValue().getId());
        }

        @Test
        @DisplayName("Create Cvs: name null")
        void testNotSuccessfulHandling() {
            CreateProffesor command = new CreateProffesor();
            //DateTimeCreated null
            command.setEmail("post@gmail.com");
            command.setLastName("Shevchenko");

            Result<ProffesorCreated> result = handler.handle(command);

            Assertions.assertFalse(result.isSuccess());
        }
    }


