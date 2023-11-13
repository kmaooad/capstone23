package edu.kmaooad.capstone23.access_rules.services;

import edu.kmaooad.capstone23.access_rules.dal.AccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import org.bson.types.ObjectId;
import java.util.Optional;
import java.util.List;
import java.util.Optional;

public interface AccessRuleService {
  AccessRule insert(AccessRule accessRule);

  void deleteRule(String id);

  List<AccessRule> findByEntityIdAndType(String entityId, AccessRuleFromEntityType entityType);

  void ban(ObjectId entityId, AccessRuleFromEntityType entityType);
  
  void updateRule(AccessRule accessRule);

  AccessRule findRuleById(String id);

  boolean existsById(String id);
  
  long delete(String query, Object... params);
  
  void update(AccessRule accessRule);
  
  Optional<AccessRule> findByIdOptional(String id);
}
