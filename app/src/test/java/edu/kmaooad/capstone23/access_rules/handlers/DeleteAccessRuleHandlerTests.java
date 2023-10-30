package edu.kmaooad.capstone23.access_rules.handlers;

import edu.kmaooad.capstone23.access_rules.commands.DeleteAccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleRepository;
import edu.kmaooad.capstone23.access_rules.events.AccessRuleDeleted;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertFalse;


import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;

@QuarkusTest
public class DeleteAccessRuleHandlerTests {

    @Inject
    AccessRuleRepository accessRuleRepository;

    @Inject
    CommandHandler<DeleteAccessRule, AccessRuleDeleted> deleteRuleHandler;

    private String accessRuleId;

    @BeforeEach
    private void prepare() {
        // Create an access rule here
        AccessRule rule = new AccessRule();
        // ... (set other properties of the rule as needed)
        accessRuleRepository.persist(rule);
        accessRuleId = rule.id.toString();
    }

    @Test
    @DisplayName("Delete Access Rule: Valid ID")
    public void deleteRuleValidId() {
        DeleteAccessRule command = new DeleteAccessRule();
        command.setId(new ObjectId(accessRuleId));

        Result<AccessRuleDeleted> result = deleteRuleHandler.handle(command);
        Assertions.assertTrue(result.isSuccess());
    }


    @Test
    @DisplayName("Delete Access Rule: non-existent ID")
    public void deleteRuleNonExistentId() {
        DeleteAccessRule command = new DeleteAccessRule();
        command.setId(new ObjectId());

        Result<AccessRuleDeleted> result = deleteRuleHandler.handle(command);
        assertFalse(result.isSuccess());
    }


}
