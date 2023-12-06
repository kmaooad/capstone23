package edu.kmaooad.capstone23.access_rules.services;

import edu.kmaooad.capstone23.access_rules.dal.AccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@ApplicationScoped
public class AccessRuleImpl implements AccessRuleService {
    @Inject
    AccessRuleRepository accessRuleRepository;
    @Override
    public AccessRule insert(AccessRule accessRule) {
        return accessRuleRepository.insert(accessRule);
    }

    @Override
    public AccessRule findById(ObjectId id) {
        return accessRuleRepository.findById(id);
    }

    @Override
    public AccessRule update(AccessRule accessRule) {
        accessRuleRepository.update(accessRule);
        return accessRule;
    }

    @Override
    public void deleteAccessRule(ObjectId accessRule) {
        accessRuleRepository.deleteRule(accessRule);
    }

    @Override
    public void ban(ObjectId entityId, AccessRuleFromEntityType fromEntityType) {
        this.accessRuleRepository.updateOnBan(entityId, fromEntityType);
    }
}
