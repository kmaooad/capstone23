package edu.kmaooad.capstone23.proffesors.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.commands.CreateGroupTemplate;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateCreated;
import edu.kmaooad.capstone23.groups.commands.CreateGroup;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import edu.kmaooad.capstone23.groups.events.GroupCreated;
import edu.kmaooad.capstone23.proffesors.commands.AddProfessorToGroup;
import edu.kmaooad.capstone23.proffesors.commands.CreateProffesor;
import edu.kmaooad.capstone23.proffesors.dal.ProffesorsRepository;
import edu.kmaooad.capstone23.proffesors.events.ProfessorAddedToGroup;
import edu.kmaooad.capstone23.proffesors.events.ProffesorCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class AddProfessorToGroupHandlerTest {

    @Inject
    ProffesorsRepository proffesorsRepository;

    @Inject
    GroupsRepository groupsRepository;

    @Inject
    CommandHandler<CreateProffesor, ProffesorCreated> createProfessorHandler;

    @Inject
    CommandHandler<CreateGroupTemplate, GroupTemplateCreated> groupTemplateCreateCommandHandler;

    @Inject
    CommandHandler<CreateGroup, GroupCreated> groupCreateCommandHandler;

    @Inject
    CommandHandler<AddProfessorToGroup, ProfessorAddedToGroup> addProfessorHandler;


    @BeforeEach
    void cleanUp() {
        proffesorsRepository.deleteAll();
        groupsRepository.deleteAll();
    }

    @Test
    @DisplayName("Add professor to group: basic handler")
    void basicRun() {
        CreateProffesor createProffesor = new CreateProffesor();
        createProffesor.setName("Masha");
        createProffesor.setEmail("post@gmail.com");
        createProffesor.setLastName("Shevchenko");


        Result<ProffesorCreated> professorCreated = createProfessorHandler.handle(createProffesor);

        Assertions.assertTrue(professorCreated.isSuccess());
        Assertions.assertNotNull(professorCreated.getValue());
        Assertions.assertNotNull(professorCreated.getValue().getId());

        CreateGroupTemplate templateCommand = new CreateGroupTemplate();
        templateCommand.setGroupTemplateName("template");
        Result<GroupTemplateCreated> resultForTemplate = groupTemplateCreateCommandHandler.handle(templateCommand);
        CreateGroup groupCommand = new CreateGroup();

        groupCommand.setGroupName("group");
        groupCommand.setTemplateId(resultForTemplate.getValue().getGroupTemplateId().toString());

        Result<GroupCreated> resultForGroup = groupCreateCommandHandler.handle(groupCommand);

        Assertions.assertTrue(resultForGroup.isSuccess());
        Assertions.assertNotNull(resultForGroup.getValue());
        Assertions.assertFalse(resultForGroup.getValue().getGroupId().isEmpty());

        AddProfessorToGroup addProfessorToGroup = new AddProfessorToGroup(professorCreated.getValue().getId(), new ObjectId(resultForGroup.getValue().getGroupId()));

        Result<ProfessorAddedToGroup> result = addProfessorHandler.handle(addProfessorToGroup);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
    }

}