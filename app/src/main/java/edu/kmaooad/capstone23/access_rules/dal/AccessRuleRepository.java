package edu.kmaooad.capstone23.access_rules.dal;

import java.util.ArrayList;
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
        List<AccessRule> listFrom = find("fromEntityId = ?1 and fromEntityType = ?2", entityId, entityType).list();
        List<AccessRule> listTo = find("toEntityId = ?1 and toEntityType = ?2", entityId, entityType).list();
        List<AccessRule> combinedList = new ArrayList<>(listFrom);
        combinedList.addAll(listTo);
        return combinedList;
    }

    public void ban(ObjectId entityId, AccessRuleFromEntityType entityType) {
        update("banned = ?1", true).where("fromEntityId = ?1 and fromEntityType = ?2", entityId, entityType);
        update("banned = ?1", true).where("toEntityId = ?1 and toEntityType = ?2", entityId, entityType);
    }

}
