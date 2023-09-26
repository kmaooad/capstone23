package edu.kmaooad.capstone23.feed_back.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.util.Date;

@MongoEntity(collection = "feedBacks")
public class FeedBack {
    public ObjectId id;
    public String topic;
    public Date date;
    public String text;
}
