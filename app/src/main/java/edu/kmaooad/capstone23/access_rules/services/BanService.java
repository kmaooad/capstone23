package edu.kmaooad.capstone23.access_rules.services;

import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import edu.kmaooad.capstone23.access_rules.events.EntityBanned;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class BanService {

    @Inject
    AccessRuleService accessRuleService;

    @Inject
    OrgsRepository orgsRepository;

    @Inject
    MembersRepository membersRepository;

    @Inject
    DepartmentsRepository departmentsRepository;

    public Result<EntityBanned> banEntity(ObjectId entityId, AccessRuleFromEntityType fromEntityType) {
        if (!entityExists(fromEntityType, entityId)) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Entity doesn't exist");
        }
        accessRuleService.ban(entityId, fromEntityType);
    
        EntityBanned bannedEvent = new EntityBanned(entityId.toString(), fromEntityType);
        return new Result<>(bannedEvent);
    }


    private boolean entityExists(AccessRuleFromEntityType fromType, ObjectId entityId) {
        return switch (fromType) {
            case Member -> membersRepository.findByIdOptional(entityId).isPresent();
            case Department -> departmentsRepository.findByIdOptional(entityId).isPresent();
            case Organisation -> orgsRepository.findByIdOptional(entityId).isPresent();
        };
    }
}
