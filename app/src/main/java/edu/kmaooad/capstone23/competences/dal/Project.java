package edu.kmaooad.capstone23.competences.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "projects")
public class Project {
    public ObjectId id;
    public String title;
}