package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.DeleteProj;
import edu.kmaooad.capstone23.competences.dal.Proj;
import edu.kmaooad.capstone23.competences.dal.ProjsRepository;
import edu.kmaooad.capstone23.competences.events.ProjDeleted;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class DeleteProjHandlerTest {
    @Inject
    DeleteProjHandler handler;
    @Inject
    ProjsRepository repository;

    @Test
    @DisplayName("Basic Handling")
    void testSuccessfulHandling() {
        Proj proj = createDefaultProj();
        repository.insert(proj);

        DeleteProj command = new DeleteProj();
        command.setId(proj.id);

        ProjDeleted projDeleted = handler.handle(command).getValue();
        assertNotNull(projDeleted);
        assertEquals(proj.id, projDeleted.projId());

        Proj deletedProj = repository.findById(proj.id);
        assertNull(deletedProj);
    }

    @Test
    @DisplayName("Delete Non-Existent Project")
    void testDeleteNonExistentProject() {
        DeleteProj command = new DeleteProj();
        command.setId(new ObjectId("5f7e4afc8e1f7112d73c92a1")); // Using a random ObjectId

        Result<?> projDeleted = handler.handle(command);
        assertFalse(projDeleted.isSuccess());
    }

    private Proj createDefaultProj() {
        Proj proj = new Proj();
        proj.name = "Test Project";
        proj.description = "Test Description";
        proj.skills = List.of(new ObjectId("5f7e47fc8e1f7112d73c92a1"));
        proj.skillSets = List.of(new ObjectId("1a4cd132b123a1aa3bc2d142"));
        return proj;
    }
}