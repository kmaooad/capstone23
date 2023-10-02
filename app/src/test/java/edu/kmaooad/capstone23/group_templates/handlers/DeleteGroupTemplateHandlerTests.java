package edu.kmaooad.capstone23.group_templates.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.commands.DeleteGroupTemplate;
import edu.kmaooad.capstone23.group_templates.commands.UpdateGroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplatesRepository;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateDeleted;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateUpdated;
import edu.kmaooad.capstone23.group_templates.handlers.DeleteGroupTemplateHandler;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeleteGroupTemplateHandlerTests {
    @Inject
    GroupsRepository groupsRepository;
    @Inject
    DeleteGroupTemplateHandler deleteGroupTemplateHandler;

    @Inject
    GroupTemplatesRepository groupTemplatesRepository;

    @Test
    @DisplayName("Delete Group Template: Basic")
    public void testSuccessfulHandling() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = "GroupTemplate";
        groupTemplatesRepository.insert(groupTemplate);

        DeleteGroupTemplate command = new DeleteGroupTemplate();
        command.setId(groupTemplate.id.toString());

        Result<GroupTemplateDeleted> result = deleteGroupTemplateHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
    }

    @Test
    @DisplayName("Delete Group Template: Group Template Does Not Exist")
    public void testDeleteNonExistentGroupTemplate() {
        DeleteGroupTemplate command = new DeleteGroupTemplate();
        command.setId(new ObjectId().toString());

        Result<GroupTemplateDeleted> result = deleteGroupTemplateHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Delete Group Template: Bad ID")
    public void testDeleteGroupTemplateWithWrongID() {
        DeleteGroupTemplate command = new DeleteGroupTemplate();
        command.setId("badID");

        Result<GroupTemplateDeleted> result = deleteGroupTemplateHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Delete Group Template: With Children")
    public void testDeleteGroupTemplateWithChildren() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = "GroupTemplate";
        groupTemplatesRepository.insert(groupTemplate);

        Group group = new Group();
        group.name = "test_group";
        group.templateId = groupTemplate.id.toString();

        groupsRepository.insert(group);

        DeleteGroupTemplate command = new DeleteGroupTemplate();
        command.setId(groupTemplate.id.toString());

        Result<GroupTemplateDeleted> result = deleteGroupTemplateHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }
}
