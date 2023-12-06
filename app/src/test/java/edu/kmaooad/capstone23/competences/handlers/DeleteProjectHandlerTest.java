package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.DeleteProject;
import edu.kmaooad.capstone23.competences.dal.Project;
import edu.kmaooad.capstone23.competences.dal.MongoProjectRepository;
import edu.kmaooad.capstone23.competences.events.ProjDeleted;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class DeleteProjectHandlerTest {
    @Inject
    DeleteProjectHandler handler;
    @Inject
    MongoProjectRepository repository;

    @Test
    @DisplayName("Basic Handling")
    void testSuccessfulHandling() {
        Project project = createDefaultProj();
        repository.insert(project);

        DeleteProject command = new DeleteProject();
        command.setId(project.id);

        ProjDeleted projDeleted = handler.handle(command).getValue();
        assertNotNull(projDeleted);
        assertEquals(project.id, projDeleted.projId());

        Project deletedProject = repository.findById(new ObjectId(project.id));
        assertNull(deletedProject);
    }

    @Test
    @DisplayName("Delete Non-Existent Project")
    void testDeleteNonExistentProject() {
        DeleteProject command = new DeleteProject();
        command.setId("5f7e4afc8e1f7112d73c92a1"); // Using a random ObjectId

        Result<?> projDeleted = handler.handle(command);
        assertFalse(projDeleted.isSuccess());
    }

    private Project createDefaultProj() {
        Project project = new Project();
        project.name = "Test Project";
        project.description = "Test Description";
        project.skills = List.of("5f7e47fc8e1f7112d73c92a1");
        project.skillSets = List.of("1a4cd132b123a1aa3bc2d142");
        return project;
    }
}