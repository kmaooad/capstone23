package edu.kmaooad.capstone23.groups.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.util.List;

@MongoEntity(collection = "groups")
public class Group {
    public ObjectId id;
    public String name;
    public String templateId;
    public List<ObjectId> activitiesId;
}
