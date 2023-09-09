package edu.kmaooad.capstone23.competences.dal;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "skill sets")
public class SkillSet {
    public ObjectId id;
    public String name;
}