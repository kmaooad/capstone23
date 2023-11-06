package edu.kmaooad.capstone23.access_rules.services;

import edu.kmaooad.capstone23.access_rules.dal.AccessRule;

public interface AccessRuleService {

    AccessRule insert(AccessRule accessRule);
    long delete(String query, Object... params);
    void update(AccessRule accessRule);
}
