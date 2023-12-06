package edu.kmaooad.capstone23.access_rules.events;

import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleToEntityType;


public record AccessRuleCreated(String id,
                                AccessRuleFromEntityType fromEntityType,
                                AccessRuleToEntityType toEntityType) { }
