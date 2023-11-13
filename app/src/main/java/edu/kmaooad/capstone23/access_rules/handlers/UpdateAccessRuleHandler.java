package edu.kmaooad.capstone23.access_rules.handlers;

import edu.kmaooad.capstone23.access_rules.commands.UpdateAccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleToEntityType;
import edu.kmaooad.capstone23.access_rules.events.AccessRuleUpdated;
import edu.kmaooad.capstone23.access_rules.services.AccessRuleService;
import edu.kmaooad.capstone23.activities.services.CourseService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.services.DepartmentService;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.services.OrgsService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class UpdateAccessRuleHandler implements CommandHandler<UpdateAccessRule, AccessRuleUpdated> {

    @Inject
    private AccessRuleService accessRuleService;

    @Inject
    private OrgsService orgsService;

    @Inject
    private MembersRepository membersRepository;

    @Inject
    private DepartmentService departmentService;

    @Inject
    private CourseService courseService;

    @Inject
    private GroupsRepository groupsRepository;

    @Override
    public Result<AccessRuleUpdated> handle(UpdateAccessRule command) {
        if(!ObjectId.isValid(command.getId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Object Id is invalid");
        if(!ObjectId.isValid(command.getFromEntityId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "From entity Id is invalid");
        if(!ObjectId.isValid(command.getToEntityId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "To entity Id is invalid");
        AccessRule accessRule = accessRuleService.findRuleById(command.getId());

        if (accessRule == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Access rule with such Id doesn't exist");
        }

        if(!fromEntityExists(command.getFromEntityType(), new ObjectId(command.getFromEntityId()))){
            return new Result<>(ErrorCode.VALIDATION_FAILED, "From entity doesn't exist");
        }

        if(!toEntityExists(command.getToEntityType(), new ObjectId(command.getToEntityId()))){
            return new Result<>(ErrorCode.VALIDATION_FAILED, "To entity doesn't exist");
        }

        accessRule.ruleType = command.getRuleType();
        accessRule.fromEntityType = command.getFromEntityType();
        accessRule.toEntityType = command.getToEntityType();
        accessRule.fromEntityId = new ObjectId(command.getFromEntityId());
        accessRule.toEntityId = new ObjectId(command.getToEntityId());

        accessRuleService.updateRule(accessRule);
        return new Result<>(new AccessRuleUpdated(accessRule.id.toString(), accessRule.ruleType, accessRule.fromEntityType,
                accessRule.fromEntityId.toString(), accessRule.toEntityType, accessRule.toEntityId.toString()));
    }

    private boolean fromEntityExists(AccessRuleFromEntityType type, ObjectId entityId) {
        return switch (type) {
            case Member -> membersRepository.findByIdOptional(entityId).isPresent();
            case Department -> departmentService.findByIdOptional(entityId).isPresent();
            case Organisation -> orgsService.findByIdOptional(entityId).isPresent();
        };
    }

    private boolean toEntityExists(AccessRuleToEntityType type, ObjectId entityId) {
        return switch (type) {
            case Group -> groupsRepository.findByIdOptional(entityId).isPresent();
            case Course -> courseService.findByIdOptional(entityId).isPresent();
            case Department -> departmentService.findByIdOptional(entityId).isPresent();
            case Organisation -> orgsService.findByIdOptional(entityId).isPresent();
        };
    }
}
