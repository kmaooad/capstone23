package edu.kmaooad.capstone23.cvs.dal;

import org.bson.types.ObjectId;

import java.util.Set;

public class JobPreference {

    public String location;
    public String industry;
    public Category category;

    public Set<ObjectId> skills;

    public enum Category {
        FULL_TIME,
        PART_TIME
    }

    public JobPreference(String location, String industry, Category category) {
        this.location = location;
        this.industry = industry;
        this.category = category;
    }

    public JobPreference() {
    }
}
