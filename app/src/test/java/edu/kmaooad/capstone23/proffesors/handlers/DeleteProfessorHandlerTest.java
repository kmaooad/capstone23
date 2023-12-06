package edu.kmaooad.capstone23.proffesors.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.proffesors.commands.CreateProffesor;
import edu.kmaooad.capstone23.proffesors.commands.DeleteProfessor;
import edu.kmaooad.capstone23.proffesors.events.ProfessorDeleted;
import edu.kmaooad.capstone23.proffesors.events.ProffesorCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class DeleteProfessorHandlerTest {

    @Inject
    CommandHandler<CreateProffesor, ProffesorCreated> createHandler;

    @Inject
    CommandHandler<DeleteProfessor, ProfessorDeleted> deleteHandler;

    @Test
    @DisplayName("Delete professor handler: basic run")
    void basicRun() {
        CreateProffesor createProffesor = new CreateProffesor();
        createProffesor.setName("Masha");
        createProffesor.setEmail("post@gmail.com");
        createProffesor.setLastName("Shevchenko");


        Result<ProffesorCreated> professorCreated = createHandler.handle(createProffesor);

        Assertions.assertTrue(professorCreated.isSuccess());
        Assertions.assertNotNull(professorCreated.getValue());
        Assertions.assertNotNull(professorCreated.getValue().getId());

        DeleteProfessor deleteProfessor = new DeleteProfessor(professorCreated.getValue().getId());
        Result<ProfessorDeleted> professorDeleted = deleteHandler.handle(deleteProfessor);

        Assertions.assertTrue(professorDeleted.isSuccess());
        Assertions.assertNotNull(professorDeleted.getValue());
        Assertions.assertNotNull(professorDeleted.getValue().getProfessorId());
    }


    @Test
    @DisplayName("Delete professor handler: \"deleted\" field in result")
    void valid() {
        CreateProffesor createProffesor = new CreateProffesor();
        createProffesor.setName("Masha");
        createProffesor.setEmail("post@gmail.com");
        createProffesor.setLastName("Shevchenko");


        Result<ProffesorCreated> professorCreated = createHandler.handle(createProffesor);

        Assertions.assertTrue(professorCreated.isSuccess());
        Assertions.assertNotNull(professorCreated.getValue());
        Assertions.assertNotNull(professorCreated.getValue().getId());

        DeleteProfessor deleteProfessor = new DeleteProfessor(professorCreated.getValue().getId());
        Result<ProfessorDeleted> professorDeleted = deleteHandler.handle(deleteProfessor);
        Result<ProfessorDeleted> falseProfessorDeleted = deleteHandler.handle(new DeleteProfessor(deleteProfessor.getProfessorId()));

        Assertions.assertTrue(professorDeleted.isSuccess());
        Assertions.assertNotNull(professorDeleted.getValue());
        Assertions.assertNotNull(professorDeleted.getValue().getProfessorId());
        Assertions.assertTrue(professorDeleted.getValue().isProfessorExisted());

        Assertions.assertTrue(falseProfessorDeleted.isSuccess());
        Assertions.assertNotNull(falseProfessorDeleted.getValue());
        Assertions.assertNotNull(falseProfessorDeleted.getValue().getProfessorId());
        Assertions.assertFalse(falseProfessorDeleted.getValue().isProfessorExisted());
    }

}