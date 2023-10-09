package edu.kmaooad.capstone23.competences.dal;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.List;

@MongoEntity(collection = "skill sets")
public class SkillSet {
    public ObjectId id;
    public String name;
    public List<ObjectId> skillIds;
}