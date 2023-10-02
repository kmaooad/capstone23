package edu.kmaooad.capstone23.access_rules.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "access-rules")
public class AccessRule {
    public ObjectId id;
    public AccessRuleType ruleType;
    public ObjectId fromEntityId;
    public AccessRuleFromEntityType fromEntityType;
    public ObjectId toEntityId;
    public AccessRuleToEntityType toEntityType;
}
