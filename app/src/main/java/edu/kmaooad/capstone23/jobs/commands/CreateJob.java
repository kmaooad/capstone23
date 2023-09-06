package edu.kmaooad.capstone23.jobs.commands;

import org.bson.types.ObjectId;

public class CreateJob {
    private ObjectId id;
    private String name;
    private boolean active;
    private ObjectId[] competencesId;
    private ObjectId[] activitiesId;

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public ObjectId[] getCompetencesId() {
        return competencesId;
    }

    public ObjectId[] getActivitiesId() {
        return activitiesId;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCompetencesId(ObjectId[] competencesId) {
        this.competencesId = competencesId;
    }

    public void setActivitiesId(ObjectId[] activitiesId) {
        this.activitiesId = activitiesId;
    }
}
