package edu.kmaooad.capstone23.groups.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplatesRepository;
import edu.kmaooad.capstone23.groups.commands.DeleteGroup;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import edu.kmaooad.capstone23.groups.events.GroupDeleted;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeleteGroupHandlerTests {

    @Inject
    GroupsRepository groupsRepository;
    @Inject
    GroupTemplatesRepository groupTemplatesRepository;
    @Inject
    CommandHandler<DeleteGroup, GroupDeleted> deleteGroupHandler;
    @Test
    @DisplayName("Delete Group : Basic")
    void testSuccessfulHandling() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = "GroupTemplate";
        groupTemplatesRepository.insert(groupTemplate);

        Group group = new Group();
        group.name = "test_group";
        group.templateId = groupTemplate.id.toString();

        groupsRepository.insert(group);

        Assertions.assertNotNull(groupsRepository.findById(group.id));

        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.setId(group.id.toString());

        Result<GroupDeleted> result = deleteGroupHandler.handle(deleteGroup);

        Assertions.assertNull(groupsRepository.findById(group.id));

        Assertions.assertTrue(result.isSuccess());
    }
}
