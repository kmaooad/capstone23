package edu.kmaooad.capstone23.activities.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.Date;

import org.bson.types.ObjectId;

@MongoEntity(collection = "activities")
public class Activity {
    public ObjectId id;
    public String name;
    public boolean inProgress;
    public boolean completed;
    public Date startDate;
    public Date finishDate;

    public Activity() {
        this.name = "activity";
        this.inProgress = true;
        this.completed = false;
        this.startDate = new Date();
        this.finishDate = new Date();
    }

    public Activity(String name, Date startDate, Date finishDate) {
        Date currentDate = new Date();
        this.name = name;
        this.inProgress = currentDate.getTime() < startDate.getTime() ? true : false;
        this.completed = false;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getName() {
        return name;
    }
}