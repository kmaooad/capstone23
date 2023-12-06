package edu.kmaooad.capstone23.access_rules.services;

import edu.kmaooad.capstone23.access_rules.dal.AccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import org.bson.types.ObjectId;

public interface AccessRuleService {
    AccessRule insert(AccessRule accessRule);
    AccessRule findById(ObjectId id);
    AccessRule update(AccessRule accessRule);
    void deleteAccessRule(ObjectId accessRule);
    void ban(ObjectId entityId, AccessRuleFromEntityType fromEntityType);
}
