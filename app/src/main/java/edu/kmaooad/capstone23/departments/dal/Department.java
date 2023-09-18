package edu.kmaooad.capstone23.departments.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.util.ArrayList;

@MongoEntity(collection = "departments")
public class Department {
    public ObjectId id;
    public String name;
    public String description;
    public String parent;
    public ArrayList<String> members;
}
