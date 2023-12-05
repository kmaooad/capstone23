package edu.kmaooad.capstone23.massages.dal;


import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "notifications")
public class Message {
    public ObjectId id;
    public String event_type;
    public String method_of_sending;
    public String messageText;

}
