package edu.kmaooad.capstone23.proffesors.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.proffesors.commands.CreateProffesor;
import edu.kmaooad.capstone23.proffesors.commands.UnassignActivity;
import edu.kmaooad.capstone23.proffesors.events.ActivityUnassigned;
import edu.kmaooad.capstone23.proffesors.events.ProffesorCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class UnassignActivityHandlerTest {
    @Inject
    CommandHandler<CreateProffesor, ProffesorCreated> proffesorHandler;

    @Inject
    UnassignActivityHandler handler;

    @Test
    @DisplayName("Unassign activity to professor: successful handling")
    void testSuccessfulHandling() {
        CreateProffesor createProffesor = new CreateProffesor();
        createProffesor.setName("Masha");
        createProffesor.setEmail("post@gmail.com");
        createProffesor.setLastName("Shevchenko");

        Result<ProffesorCreated> proffesorCreatedResult = proffesorHandler.handle(createProffesor);

        UnassignActivity command = new UnassignActivity();
        command.setActivity(new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa"));
        command.setProfessor(proffesorCreatedResult.getValue().getId());


        Result<ActivityUnassigned> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertNotNull(result.getValue().getProfessorId());
    }

    @Test
    @DisplayName("Unassign activity to professor: non existent professor")
    void testNotSuccessfulHandling() {
        UnassignActivity command = new UnassignActivity();
        command.setActivity(new ObjectId());
        command.setProfessor(new ObjectId());


        Result<ActivityUnassigned> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertNull(result.getValue());
    }
}