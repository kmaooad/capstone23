package edu.kmaooad.capstone23.access_rules.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccessRuleRepository implements PanacheMongoRepository<AccessRule> {

    public AccessRule insert(AccessRule accessRule) {
        persist(accessRule);
        return accessRule;
    }
}
