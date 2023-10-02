package edu.kmaooad.capstone23.orgs.dal;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.ArrayList;

@MongoEntity(collection = "orgs")
public class Org {
    public ObjectId id;
    public String name;
    public String industry;
    public String website;
    public String description;
    public String emailDomain;
    public String hiringStatus;

    public ArrayList<String> jobs;
    public Boolean isActive;
}
