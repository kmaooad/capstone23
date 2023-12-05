package edu.kmaooad.capstone23.feed_back.notifications.dal;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@MongoEntity(collection = "feed_back_notification")
public class Notification {
    public ObjectId id;
    public String feedBackId;
    public String type;
    public String to;
    public LocalDateTime dateTimeCreated;
}

