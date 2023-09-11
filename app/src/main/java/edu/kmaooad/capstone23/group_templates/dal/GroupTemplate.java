package edu.kmaooad.capstone23.group_templates.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "grouptemplates")
public class GroupTemplate {
    public ObjectId id;
    public String name;
}
