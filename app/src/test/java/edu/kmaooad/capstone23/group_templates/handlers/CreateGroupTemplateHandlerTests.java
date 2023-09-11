package edu.kmaooad.capstone23.group_templates.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.commands.CreateGroupTemplate;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.testng.annotations.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

@QuarkusTest
public class CreateGroupTemplateHandlerTests {
    @Inject
    CommandHandler<CreateGroupTemplate, GroupTemplateCreated> handler;

    @Test
    @DisplayName("Create Group Template: Basic")
    public void testSuccessfulHandling() {
        CreateGroupTemplate command = new CreateGroupTemplate();
        command.setGroupTemplateName("testGroup");

        Result<GroupTemplateCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getGroupTemplateId().isEmpty());
    }

    @Test
    @DisplayName("Create Group Template: Name Validation")
    public void testNameValidation() {
        CreateGroupTemplate command = new CreateGroupTemplate();
        command.setGroupTemplateName("t");

        Result<GroupTemplateCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }
}
