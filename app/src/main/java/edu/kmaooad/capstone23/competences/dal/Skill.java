package edu.kmaooad.capstone23.competences.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "skills")
public class Skill {

    public ObjectId parentSkill;
    public ObjectId id;
    public String name;

    public void setId(String id)
    {
        this.id = new ObjectId(id);
    }
  
    public void setParentSkill(String id)
    {
        this.parentSkill = new ObjectId(id);
    }
}
