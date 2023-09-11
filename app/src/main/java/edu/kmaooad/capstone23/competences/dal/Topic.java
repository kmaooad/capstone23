package edu.kmaooad.capstone23.competences.dal;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "topics")
public class Topic {
    public ObjectId id;
    public String name;
    public String parentId;
}
