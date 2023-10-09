package edu.kmaooad.capstone23.access_rules.events;

import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleToEntityType;
import org.bson.types.ObjectId;

public record AccessRuleCreated(ObjectId id,
                                AccessRuleFromEntityType fromEntityType,
                                AccessRuleToEntityType toEntityType) { }
