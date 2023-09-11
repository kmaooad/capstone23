package edu.kmaooad.capstone23.competences.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "skills")
public class Skill {

    public ObjectId parentSkill;
    public ObjectId id;
    public String name;
}
