package edu.kmaooad.capstone23.groups.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.commands.UpdateGroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplatesRepository;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateUpdated;
import edu.kmaooad.capstone23.group_templates.handlers.UpdateGroupTemplateHandler;
import edu.kmaooad.capstone23.groups.commands.UpdateGroup;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import edu.kmaooad.capstone23.groups.events.GroupUpdated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateGroupHandlerTests {

    @Inject
    GroupTemplatesRepository groupTemplatesRepository;

    @Inject
    GroupsRepository groupsRepository;
    @Inject
    UpdateGroupHandler updateGroupHandler;
    @Test
    @DisplayName("Update Group : Basic")
    public void testSuccessfulHandling() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = "GroupTemplate";
        groupTemplatesRepository.insert(groupTemplate);

        Group group = new Group();
        group.name = "test_group";
        group.templateId = groupTemplate.id.toString();

        groupsRepository.insert(group);

        UpdateGroup command = new UpdateGroup();
        command.setId(group.id.toString());
        command.setTemplateId(groupTemplate.id.toString());
        command.setGroupName("new_name");

        Result<GroupUpdated> result = updateGroupHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getId().isEmpty());
        Assertions.assertFalse(result.getValue().getName().isEmpty());
    }
}
