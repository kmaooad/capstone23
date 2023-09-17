package edu.kmaooad.capstone23.relations.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;


@MongoEntity(collection = "relations")
public class Relation {
    private ObjectId id;
    private final ObjectId firstObjectId;
    private final ObjectId secondObjectId;

    public Relation(ObjectId firstObjectId, ObjectId secondObjectId) {
        this.firstObjectId = firstObjectId;
        this.secondObjectId = secondObjectId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getFirstObjectId() {
        return firstObjectId;
    }

    public ObjectId getSecondObjectId() {
        return secondObjectId;
    }
}
