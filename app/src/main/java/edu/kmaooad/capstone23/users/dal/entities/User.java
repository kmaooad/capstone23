package edu.kmaooad.capstone23.users.dal.entities;

import edu.kmaooad.capstone23.notifications.models.Event;
import edu.kmaooad.capstone23.notifications.models.NotificationMethod;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.ArrayList;

@MongoEntity(collection = "users")
public class User {
    public ObjectId id;
    public String firstName;
    public String lastName;
    @BsonProperty("unique_email")
    public String email;
    public ArrayList<NotificationMethod> notificationMethods;
    public ArrayList<Event> notificationEvents;
}
