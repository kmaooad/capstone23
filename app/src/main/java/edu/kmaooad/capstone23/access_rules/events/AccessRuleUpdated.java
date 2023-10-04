package edu.kmaooad.capstone23.access_rules.events;

import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleToEntityType;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleType;

public record AccessRuleUpdated (String id, AccessRuleType ruleType, AccessRuleFromEntityType fromEntityType, String fromId,
                                 AccessRuleToEntityType toEntityType, String toId) {}
