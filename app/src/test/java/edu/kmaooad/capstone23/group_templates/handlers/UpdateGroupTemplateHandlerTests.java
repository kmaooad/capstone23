package edu.kmaooad.capstone23.group_templates.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.commands.UpdateGroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplatesRepository;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateUpdated;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UpdateGroupTemplateHandlerTests {
    @Inject
    UpdateGroupTemplateHandler updateGroupTemplateHandler;

    @Inject
    GroupTemplatesRepository groupTemplatesRepository;

    @Test
    @DisplayName("Update Group Template: Basic")
    public void testSuccessfulHandling() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = "GroupTemplate";
        groupTemplatesRepository.insert(groupTemplate);

        UpdateGroupTemplate command = new UpdateGroupTemplate();
        command.setId(groupTemplate.id.toString());
        command.setGroupTemplateName("RenamedGroupTemplate");

        Result<GroupTemplateUpdated> result = updateGroupTemplateHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getId().isEmpty());
        Assertions.assertFalse(result.getValue().getName().isEmpty());
    }
    @Test
    @DisplayName("Update Group Template: Group Template does not exist")
    public void testNonExistentGroupTemplate() {

        UpdateGroupTemplate command = new UpdateGroupTemplate();
        command.setId("badId");
        command.setGroupTemplateName("RenamedGroupTemplate");

        Result<GroupTemplateUpdated> result = updateGroupTemplateHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Update Group Template: Name Validation")
    public void testInvalidGroupTemplateName() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = "GroupTemplate";
        groupTemplatesRepository.insert(groupTemplate);

        UpdateGroupTemplate command = new UpdateGroupTemplate();
        command.setId(groupTemplate.id.toString());
        command.setGroupTemplateName("a");

        Result<GroupTemplateUpdated> result = updateGroupTemplateHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }
}
