package edu.kmaooad.capstone23.activities.dal;

import edu.kmaooad.capstone23.tag.dal.Tag;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.util.List;

@MongoEntity(collection = "courses")
public class Course {
    public ObjectId id;
    public String name;
    public List<Tag> tags;
}
