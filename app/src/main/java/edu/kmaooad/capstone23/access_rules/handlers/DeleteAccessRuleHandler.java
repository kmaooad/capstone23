package edu.kmaooad.capstone23.access_rules.handlers;

import edu.kmaooad.capstone23.access_rules.commands.DeleteAccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleRepository;
import edu.kmaooad.capstone23.access_rules.events.AccessRuleDeleted;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class DeleteAccessRuleHandler implements CommandHandler<DeleteAccessRule, AccessRuleDeleted> {

    @Inject
    private AccessRuleRepository accessRuleRepository;

    public Result<AccessRuleDeleted> handle(DeleteAccessRule command) {
        if(!accessRuleRepository.findByIdOptional(command.getId()).isPresent()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Access rule doesn't exist");
        }

        accessRuleRepository.delete("id", command.getId());
        return new Result<>(new AccessRuleDeleted(command.getId()));
    }
}
