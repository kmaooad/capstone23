package edu.kmaooad.capstone23.access_rules.handlers;

import edu.kmaooad.capstone23.access_rules.commands.DeleteAccessRule;
import edu.kmaooad.capstone23.access_rules.events.AccessRuleDeleted;
import edu.kmaooad.capstone23.access_rules.services.AccessRuleService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class DeleteAccessRuleHandler implements CommandHandler<DeleteAccessRule, AccessRuleDeleted> {

    @Inject
    AccessRuleService accessRuleService;

    public Result<AccessRuleDeleted> handle(DeleteAccessRule command) {
        if (accessRuleService == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Access rule doesn't exist");
        }

        ObjectId id = ObjectId.isValid(command.getId()) ? new ObjectId(command.getId()) : null;
        accessRuleService.deleteAccessRule(id);
        return new Result<>(new AccessRuleDeleted(command.getId()));
    }
}
