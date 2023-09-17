package edu.kmaooad.capstone23.activities.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.kmaooad.capstone23.activities.commands.AssignActivityToGroup;
import edu.kmaooad.capstone23.activities.commands.CreateActivity;
import edu.kmaooad.capstone23.activities.events.ActivityCreated;
import edu.kmaooad.capstone23.activities.events.AssignActivityToGroupEvent;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.commands.CreateGroupTemplate;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class AssignActivityToGroupHandlerTest {
    @Inject
    CommandHandler<CreateGroupTemplate, GroupTemplateCreated> groupsHandler;

    @Inject
    CommandHandler<CreateActivity, ActivityCreated> activityHandler;

    @Inject
    CommandHandler<AssignActivityToGroup, AssignActivityToGroupEvent> handler;

    @Test
    @DisplayName("Assign Activity To a Group")
    public void testSuccessfulGroupHandling() {
        AssignActivityToGroup command = new AssignActivityToGroup();
        CreateGroupTemplate createGroupCommand = new CreateGroupTemplate();
        createGroupCommand.setGroupTemplateName("testGroup");

        Result<GroupTemplateCreated> groupResult = groupsHandler.handle(createGroupCommand);

        CreateActivity createActivityCommand = new CreateActivity();
        createActivityCommand.setActivitytName("Matematika");
        Result<ActivityCreated> activityResult = activityHandler.handle(createActivityCommand);

        command.setGroupId(groupResult.getValue().getGroupTemplateId());
        command.setActivityId(activityResult.getValue().getId());

        Result<AssignActivityToGroupEvent> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(groupResult.getValue().getGroupTemplateId().isEmpty());
    }

    @Test
    @DisplayName("Unassign Activity To a Group")
    public void testUnsuccessfulGroupHandling() {
        AssignActivityToGroup command = new AssignActivityToGroup();
        CreateGroupTemplate createGroupCommand = new CreateGroupTemplate();
        createGroupCommand.setGroupTemplateName("GroupB");

        Result<GroupTemplateCreated> groupResult = groupsHandler.handle(createGroupCommand);

        CreateActivity createActivityCommand = new CreateActivity();
        createActivityCommand.setActivitytName("Germany");
        Result<ActivityCreated> activityResult = activityHandler.handle(createActivityCommand);

        command.setGroupId(groupResult.getValue().getGroupTemplateId());
        command.setActivityId(activityResult.getValue().getId());

        Result<AssignActivityToGroupEvent> result = handler.handle(command);

        Assertions.assertNotNull(result.getValue());
        Assertions.assertTrue(result.isSuccess());
    }
}
