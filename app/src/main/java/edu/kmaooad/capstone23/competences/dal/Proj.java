package edu.kmaooad.capstone23.competences.dal;
import org.bson.types.ObjectId;
import java.util.List;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "projs")
public class Proj {
    public ObjectId id;
    public String name;
    public String description;
    public List<String> skills;
    public List<String> skillSets;

}
