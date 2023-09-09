package edu.kmaooad.capstone23.departments.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "departments")
public class Departments {
    public ObjectId id;
    public String name;
    public String description;
    public String parent;
}
