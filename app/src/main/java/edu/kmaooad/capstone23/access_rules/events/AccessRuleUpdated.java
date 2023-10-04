package edu.kmaooad.capstone23.access_rules.events;

import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleToEntityType;

public record AccessRuleUpdated (String id, String ruleType, AccessRuleFromEntityType fromEntityType, String fromId,
                                 AccessRuleToEntityType toEntityType, String toId) {}
