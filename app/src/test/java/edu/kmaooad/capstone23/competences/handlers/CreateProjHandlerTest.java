package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.competences.commands.CreateProj;
import edu.kmaooad.capstone23.competences.events.ProjCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class CreateProjHandlerTest {
    @Inject
    CommandHandler<CreateProj, ProjCreated> handler;

    @Test
    @DisplayName("Basic Handling")
    void testSuccessfulHandling() {
        var command = createDefaultCommand();
        var result = handler.handle(command);
        assertTrue(result.isSuccess());
        assertNotNull(result.getValue());
        assertFalse(result.getValue().projId().isEmpty());
    }

    @Test
    @DisplayName("Validation")
    void testInvalidInput() {
        var command = createDefaultCommand();
        command.setName("Some_Project"); // setting invalid name

        var result = handler.handle(command);
        assertFalse(result.isSuccess());
    }

    private CreateProj createDefaultCommand() {
        var command = new CreateProj();
        command.setName("SomeProject");
        command.setDescription("Some description of some project");
        command.setSkills(List.of(
                new ObjectId("5f7e47fc8e1f7112d73c92a1")
        ));

        command.setSkillSets(List.of(
                new ObjectId("1a4cd132b123a1aa3bc2d142")
        ));

        return command;
    }
}