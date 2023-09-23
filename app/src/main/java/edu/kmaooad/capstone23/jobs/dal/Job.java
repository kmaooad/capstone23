package edu.kmaooad.capstone23.jobs.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.util.ArrayList;

@MongoEntity(collection = "jobs")
public class Job {
    public ObjectId id;
    public String name;
    public boolean active;
    public ArrayList<ObjectId> competencesId;
    public ArrayList<ObjectId> activitiesId;

}
