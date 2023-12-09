package edu.kmaooad.capstone23.access_rules.services;

import edu.kmaooad.capstone23.access_rules.dal.AccessRule;
import org.bson.types.ObjectId;
import java.util.Optional;

public interface AccessRuleService {
    public AccessRule insert(AccessRule accessRule);
    public AccessRule findById(ObjectId id);
    public AccessRule update(AccessRule accessRule);
    public void deleteAccessRule(ObjectId accessRule);
}
