package edu.kmaooad.capstone23.ban.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "entity-ban")
public class EntityBan {
    public ObjectId id;
    public ObjectId entityId;
    public BannedEntityType entityType;
    public String reason;
}
