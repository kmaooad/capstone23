package edu.kmaooad.capstone23.access_rules.events;

import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import org.bson.types.ObjectId;

public record EntityBanned(String entityId,
                           AccessRuleFromEntityType entityType) {
}
