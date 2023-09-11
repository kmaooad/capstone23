package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.competences.commands.UpdateProj;
import edu.kmaooad.capstone23.competences.dal.Proj;
import edu.kmaooad.capstone23.competences.dal.ProjsRepository;
import edu.kmaooad.capstone23.competences.events.ProjUpdated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class UpdateProjHandlerTest {
    @Inject
    CommandHandler<UpdateProj, ProjUpdated> handler;
    @Inject
    ProjsRepository repository;

    @Test
    @DisplayName("Basic Updating")
    public void basicProjUpdatingTest() {
        var projToInsert = createDefaultProj();
        var originalProj = repository.insert(projToInsert);

        Proj updatedProj = new Proj();
        updatedProj.name = "Updated Project";
        updatedProj.description = "Updated Description";
        updatedProj.skills = List.of(new ObjectId("1a4cd132b123a1aa3bc2d142"));
        updatedProj.skillSets = List.of(new ObjectId("5f7e47fc8e1f7112d73c92a1"));

        var command = new UpdateProj();
        command.setId(originalProj.id);
        command.setName(updatedProj.name);
        command.setDescription(updatedProj.description);
        command.setSkills(updatedProj.skills);
        command.setSkillSets(updatedProj.skillSets);

        var result = handler.handle(command);
        assertTrue(result.isSuccess());

        var updateEvent = result.getValue();
        assertEquals(updateEvent.name(), updatedProj.name);
        assertEquals(updateEvent.description(), updatedProj.description);
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
        command.setSkills(projToInsert.skills);
        command.setSkillSets(projToInsert.skillSets);

        var result = handler.handle(command);
        assertFalse(result.isSuccess());

        var originalAfterUpdating = repository.findById(originalProj.id);
        assertNotEquals(originalAfterUpdating.name, updatedName);
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