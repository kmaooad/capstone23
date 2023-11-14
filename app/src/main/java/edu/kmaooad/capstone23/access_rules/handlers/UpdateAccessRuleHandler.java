package edu.kmaooad.capstone23.access_rules.handlers;

import edu.kmaooad.capstone23.access_rules.commands.UpdateAccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleToEntityType;
import edu.kmaooad.capstone23.access_rules.events.AccessRuleUpdated;
import edu.kmaooad.capstone23.access_rules.services.AccessRuleService;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.groups.services.GroupService;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@RequestScoped
public class UpdateAccessRuleHandler implements CommandHandler<UpdateAccessRule, AccessRuleUpdated> {

    @Inject
    private AccessRuleService accessRuleService;

    @Inject
    private OrgsRepository orgsRepository;

    @Inject
    private MembersRepository membersRepository;

    @Inject
    private DepartmentsRepository departmentsRepository;

    @Inject
    private CourseRepository courseRepository;

    @Inject
    private GroupService groupService;

    @Override
    public Result<AccessRuleUpdated> handle(UpdateAccessRule command) {
        if(!ObjectId.isValid(command.getId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Object Id is invalid");
        if(!ObjectId.isValid(command.getFromEntityId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "From entity Id is invalid");
        if(!ObjectId.isValid(command.getToEntityId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "To entity Id is invalid");
        Optional<AccessRule> accessRuleOpt = accessRuleService.findByIdOptional(command.getId());

        if (!accessRuleOpt.isPresent()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Access rule with such Id doesn't exist");
        }
        AccessRule accessRule = accessRuleOpt.get();
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
      
        accessRuleService.update(accessRule);
        return new Result<>(new AccessRuleUpdated(accessRule.id.toString(), accessRule.ruleType, accessRule.fromEntityType,
                accessRule.fromEntityId.toString(), accessRule.toEntityType, accessRule.toEntityId.toString()));
    }

    private boolean fromEntityExists(AccessRuleFromEntityType type, ObjectId entityId) {
        return switch (type) {
            case Member -> membersRepository.findByIdOptional(entityId).isPresent();
            case Department -> departmentsRepository.findByIdOptional(entityId).isPresent();
            case Organisation -> orgsRepository.findByIdOptional(entityId).isPresent();
        };
    }

    private boolean toEntityExists(AccessRuleToEntityType type, ObjectId entityId) {
        return switch (type) {
            case Group -> groupService.findByIdOptional(entityId.toString()).isPresent();
            case Course -> courseRepository.findByIdOptional(entityId).isPresent();
            case Department -> departmentsRepository.findByIdOptional(entityId).isPresent();
            case Organisation -> orgsRepository.findByIdOptional(entityId).isPresent();
        };
    }
}
