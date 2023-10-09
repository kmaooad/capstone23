package edu.kmaooad.capstone23.proffesors.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.groups.commands.CreateGroup;
import edu.kmaooad.capstone23.groups.events.GroupCreated;
import edu.kmaooad.capstone23.proffesors.commands.AddProfessorToGroup;
import edu.kmaooad.capstone23.proffesors.commands.CreateProffesor;
import edu.kmaooad.capstone23.proffesors.events.ProfessorAddedToGroup;
import edu.kmaooad.capstone23.proffesors.events.ProffesorCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class AddProfessorToGroupHandlerTest {
    @Inject
    CommandHandler<CreateProffesor, ProffesorCreated> createProfessorHandler;

    @Inject
    CommandHandler<CreateGroup, GroupCreated> groupCreateCommandHandler;

    @Inject
    CommandHandler<AddProfessorToGroup, ProfessorAddedToGroup> addProfessorHandler;


    @Test
    @DisplayName("Add professor to group: basic handler")
    void basicRun() {
        
    }

}