package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.competences.commands.UpdateProj;
import edu.kmaooad.capstone23.competences.dal.Project;
import edu.kmaooad.capstone23.competences.dal.MongoProjectRepository;
import edu.kmaooad.capstone23.competences.events.ProjUpdated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class UpdateProjectHandlerTest {
    @Inject
    CommandHandler<UpdateProj, ProjUpdated> handler;
    @Inject
    MongoProjectRepository repository;

    @Test
    @DisplayName("Basic Updating")
    public void basicProjUpdatingTest() {
        var projToInsert = createDefaultProj();
        var originalProj = repository.insert(projToInsert);

        Project updatedProject = new Project();
        updatedProject.name = "Updated Project";
        updatedProject.description = "Updated Description";
        updatedProject.skills = List.of("1a4cd132b123a1aa3bc2d142");
        updatedProject.skillSets = List.of("5f7e47fc8e1f7112d73c92a1");

        var command = new UpdateProj();
        command.setId(originalProj.id);
        command.setName(updatedProject.name);
        command.setDescription(updatedProject.description);
        command.setSkills(updatedProject.skills.stream().map(ObjectId::new).toList());
        command.setSkillSets(updatedProject.skillSets.stream().map(ObjectId::new).toList());

        var result = handler.handle(command);
        assertTrue(result.isSuccess());

        var updateEvent = result.getValue();
        assertEquals(updateEvent.name(), updatedProject.name);
        assertEquals(updateEvent.description(), updatedProject.description);
    }


    @Test
    @DisplayName("Name Validation")
    public void validationTest() {
        var projToInsert = createDefaultProj();
        var originalProj = repository.insert(projToInsert);

        var updatedName = "Updated_name";
        projToInsert.name = updatedName;

        var command = new UpdateProj();
        command.setId(originalProj.id);
        command.setName(projToInsert.name);
        command.setDescription(projToInsert.description);
        command.setSkills(projToInsert.skills.stream().map(ObjectId::new).toList());
        command.setSkillSets(projToInsert.skillSets.stream().map(ObjectId::new).toList());

        var result = handler.handle(command);
        assertFalse(result.isSuccess());

        var originalAfterUpdating = repository.findById(originalProj.id);
        assertNotEquals(originalAfterUpdating.name, updatedName);
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