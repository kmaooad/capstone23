package edu.kmaooad.capstone23.groups.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.commands.CreateGroupTemplate;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateCreated;
import edu.kmaooad.capstone23.groups.commands.CreateGroup;
import edu.kmaooad.capstone23.groups.events.GroupCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateGroupHandlerTests {

    @Inject
    CommandHandler<CreateGroupTemplate, GroupTemplateCreated> templateHandler;

    @Inject
    CommandHandler<CreateGroup, GroupCreated> groupHandler;

    @Test
    @DisplayName("Create Group Template: Basic")
    public void testSuccessfulHandling() {
        CreateGroupTemplate templateCommand = new CreateGroupTemplate();
        templateCommand.setGroupTemplateName("template");
        Result<GroupTemplateCreated> resultForTemplate = templateHandler.handle(templateCommand);

        CreateGroup groupCommand = new CreateGroup("group", resultForTemplate.getValue().getGroupTemplateId().toString());
        Result<GroupCreated> resultForGroup = groupHandler.handle(groupCommand);

        Assertions.assertTrue(resultForGroup.isSuccess());
        Assertions.assertNotNull(resultForGroup.getValue());
        Assertions.assertFalse(resultForGroup.getValue().getGroupId().isEmpty());
    }
    @Test
    @DisplayName("Create Group Template: Name Validation")
    public void testNameValidation() {
        CreateGroupTemplate templateCommand = new CreateGroupTemplate();
        templateCommand.setGroupTemplateName("template");
        Result<GroupTemplateCreated> resultForTemplate = templateHandler.handle(templateCommand);

        CreateGroup groupCommand = new CreateGroup("", resultForTemplate.getValue().getGroupTemplateId().toString());
        Result<GroupCreated> resultForGroup = groupHandler.handle(groupCommand);

        Assertions.assertFalse(resultForGroup.isSuccess());
    }
    @Test
    @DisplayName("Create Group Template: Template Validation")
    public void testTemplateForGroupValidation() {

        CreateGroup groupCommand = new CreateGroup("group", "123abcdef");
        Result<GroupCreated> resultForGroup = groupHandler.handle(groupCommand);

        Assertions.assertFalse(resultForGroup.isSuccess());
    }
}
