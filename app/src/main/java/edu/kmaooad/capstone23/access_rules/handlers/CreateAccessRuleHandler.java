package edu.kmaooad.capstone23.access_rules.handlers;

import edu.kmaooad.capstone23.access_rules.commands.CreateAccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleToEntityType;
import edu.kmaooad.capstone23.access_rules.events.AccessRuleCreated;
import edu.kmaooad.capstone23.access_rules.services.AccessRuleService;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import edu.kmaooad.capstone23.groups.services.GroupService;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class CreateAccessRuleHandler implements CommandHandler<CreateAccessRule, AccessRuleCreated> {

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
    private GroupService groupsService;

    public Result<AccessRuleCreated> handle(CreateAccessRule command) {
        if(!ObjectId.isValid(command.getFromEntityId())) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Invalid From entity ID");
        }
        if(!ObjectId.isValid(command.getToEntityId())) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Invalid To entity ID");
        }
        if(!fromEntityExists(command.getFromEntityType(), new ObjectId(command.getFromEntityId()))){
            return new Result<>(ErrorCode.VALIDATION_FAILED, "From entity doesn't exist");
        }

        if(!toEntityExists(command.getToEntityType(), new ObjectId(command.getToEntityId()))){
            return new Result<>(ErrorCode.VALIDATION_FAILED, "To entity doesn't exist");
        }

        AccessRule accessRule = new AccessRule();
        accessRule.ruleType = command.getRuleType();
        accessRule.fromEntityType = command.getFromEntityType();
        accessRule.toEntityType = command.getToEntityType();
        accessRule.fromEntityId = new ObjectId(command.getFromEntityId());
        accessRule.toEntityId = new ObjectId(command.getToEntityId());

        accessRuleService.insert(accessRule);

        return new Result<>(new AccessRuleCreated(accessRule.id.toString(), accessRule.fromEntityType, accessRule.toEntityType));
    }

    boolean fromEntityExists(AccessRuleFromEntityType type, ObjectId entityId) {
        return switch (type) {
            case Member -> membersRepository.findByIdOptional(entityId).isPresent();
            case Department -> departmentsRepository.findByIdOptional(entityId).isPresent();
            case Organisation -> orgsRepository.findByIdOptional(entityId).isPresent();
        };
    }

    boolean toEntityExists(AccessRuleToEntityType type, ObjectId entityId) {
        return switch (type) {
            case Group -> groupsService.findByIdOptional(entityId.toString()).isPresent();
            case Course -> courseRepository.findByIdOptional(entityId).isPresent();
            case Department -> departmentsRepository.findByIdOptional(entityId).isPresent();
            case Organisation -> orgsRepository.findByIdOptional(entityId).isPresent();
        };
    }
}
