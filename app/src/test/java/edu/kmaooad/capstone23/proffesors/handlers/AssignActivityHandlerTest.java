package edu.kmaooad.capstone23.proffesors.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.proffesors.commands.AssignActivity;
import edu.kmaooad.capstone23.proffesors.commands.CreateProffesor;
import edu.kmaooad.capstone23.proffesors.events.ActivityAssigned;
import edu.kmaooad.capstone23.proffesors.events.ProffesorCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AssignActivityHandlerTest {

    @Inject
    CommandHandler<CreateProffesor, ProffesorCreated> proffesorHandler;

    @Inject
    AssignActivityHandler handler;

    @Test
    @DisplayName("Assign activity to professor: successful handling")
    void testSuccessfulHandling() {
        CreateProffesor createProffesor = new CreateProffesor();
        createProffesor.setName("Masha");
        createProffesor.setEmail("post@gmail.com");
        createProffesor.setLastName("Shevchenko");

        Result<ProffesorCreated> proffesorCreatedResult = proffesorHandler.handle(createProffesor);

        AssignActivity command = new AssignActivity();
        command.setActivity(new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa"));
        command.setProfessor(proffesorCreatedResult.getValue().getId());


        Result<ActivityAssigned> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertNotNull(result.getValue().getProfessorId());
    }

    @Test
    @DisplayName("Assign activity to professor: non existent professor")
    void testNotSuccessfulHandling() {
        AssignActivity command = new AssignActivity();
        command.setActivity(new ObjectId());
        command.setProfessor(new ObjectId());


        Result<ActivityAssigned> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertNull(result.getValue());
    }
}