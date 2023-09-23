package edu.kmaooad.capstone23.activities.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "extraActivity")
public class ExtraActivity {
    public ObjectId id;
    public String name;


}
