package edu.kmaooad.capstone23.proffesors.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import edu.kmaooad.capstone23.proffesors.commands.CreateProffesor;
import edu.kmaooad.capstone23.proffesors.commands.UpdateProfessor;
import edu.kmaooad.capstone23.proffesors.events.ProfessorUpdated;
import edu.kmaooad.capstone23.proffesors.events.ProffesorCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class UpdateProfessorHandlerTest {

    @Inject
    CommandHandler<CreateProffesor, ProffesorCreated> createHandler;

    @Inject
    CommandHandler<UpdateProfessor, ProfessorUpdated> updateHandler;

    @Test
    @DisplayName("Update professor test: basic test")
    void basicTest() {
        CreateProffesor createProffesor = new CreateProffesor();
        createProffesor.setName("Masha");
        createProffesor.setLastName("Shevchenko");
        createProffesor.setEmail("post@gmail.com");

        Result<ProffesorCreated> createdResult = createHandler.handle(createProffesor);
        Assertions.assertTrue(createdResult.isSuccess());



        UpdateProfessor updateCommand = new UpdateProfessor(createdResult.getValue().getId());
        updateCommand.setFirstName("Marichka");
        updateCommand.setLastName("Deineka");
        updateCommand.setEmail("marichkadeineka@gmail.com");
        updateCommand.setPreference(new JobPreference());

        Result<ProfessorUpdated> updatedResult = updateHandler.handle(updateCommand);

        Assertions.assertNotNull(updatedResult);
        Assertions.assertTrue(updatedResult.isSuccess());
        Assertions.assertNotNull(updatedResult.getValue());
        Assertions.assertEquals(updatedResult.getValue().getProfessorId(), createdResult.getValue().getId());
    }

}