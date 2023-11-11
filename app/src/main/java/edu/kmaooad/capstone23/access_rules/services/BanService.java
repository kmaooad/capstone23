package edu.kmaooad.capstone23.access_rules.services;

import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import edu.kmaooad.capstone23.access_rules.events.EntityBanned;
import edu.kmaooad.capstone23.common.Result;
import org.bson.types.ObjectId;

public interface BanService {
    Result<EntityBanned> banEntity(ObjectId entityId, AccessRuleFromEntityType fromEntityType);
}
