package edu.kmaooad.capstone23.access_rules.services;

import edu.kmaooad.capstone23.access_rules.dal.AccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import org.bson.types.ObjectId;

import java.util.List;

public interface AccessRuleService {
  AccessRule insert(AccessRule accessRule);

  void deleteRule(ObjectId id);

  List<AccessRule> findByEntityIdAndType(ObjectId entityId, AccessRuleFromEntityType entityType);

  void ban(ObjectId entityId, AccessRuleFromEntityType entityType);
  
  void updateRule(AccessRule accessRule);

  AccessRule findRuleById(String id);
}
