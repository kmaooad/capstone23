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

        @DisplayName("Create Proffesor: successful handling")

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
        @DisplayName("Create Proffesor: name null")
        void testNotSuccessfulHandling() {
            CreateProffesor command = new CreateProffesor();
            //DateTimeCreated null
            command.setEmail("post@gmail.com");
            command.setLastName("Shevchenko");

            Result<ProffesorCreated> result = handler.handle(command);

            Assertions.assertFalse(result.isSuccess());
        }

        @Test
        @DisplayName("Create Proffesor:not successful handling")
        void testnotSuccessfulWithoutEmail() {
            CreateProffesor command = new CreateProffesor();
            command.setName("Masha");
            command.setLastName("Shevchenko");


            Result<ProffesorCreated> result = handler.handle(command);

            Assertions.assertFalse(result.isSuccess());
        }

        @Test
        @DisplayName("Create Proffesors: email not right")
        void testNotRightemail() {
            CreateProffesor command = new CreateProffesor();

            command.setName("Masha");
            command.setEmail("post...gmail.com");
            command.setLastName("Shevchenko");

            Result<ProffesorCreated> result = handler.handle(command);

            Assertions.assertFalse(result.isSuccess());
        }

        @Test
        @DisplayName("Create Proffesors: lastname null")
        void testNoLastname() {
            CreateProffesor command = new CreateProffesor();

            command.setName("Masha");
            command.setEmail("post@gmail.com");

            Result<ProffesorCreated> result = handler.handle(command);

            Assertions.assertFalse(result.isSuccess());
        }

        @Test
        @DisplayName("Create Proffesors: lastname not right")
        void testNotRightLastname() {
            CreateProffesor command = new CreateProffesor();

            command.setName("Masha");
            command.setLastName("ehd;iwehfuigfuigfuifghkdjgfkljdfgsjdgfjsgfjsdgfslgfsugfu");
            command.setEmail("post@gmail.com");

            Result<ProffesorCreated> result = handler.handle(command);

            Assertions.assertFalse(result.isSuccess());
        }

    }


