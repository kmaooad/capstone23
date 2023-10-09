package edu.kmaooad.capstone23.access_rules.dal;

import java.util.List;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccessRuleRepository implements PanacheMongoRepository<AccessRule> {

    public AccessRule insert(AccessRule accessRule) {
        persist(accessRule);
        return accessRule;
    }

    public void deleteRule(ObjectId id) {
        delete("id", id);
    }

    public List<AccessRule> findByEntityIdAndType(ObjectId entityId, AccessRuleFromEntityType entityType) {
        return find("entityId = ?1 and entityType = ?2", entityId, entityType).list();
    }

    public void ban(ObjectId entityId, String entityType) {
        update("banned = true where fromEntityId = ?1 and fromEntityType = ?2", entityId, entityType);
        update("banned = true where toEntityId = ?1 and toEntityType = ?2", entityId, entityType);
    }

}
