package edu.kmaooad.capstone23.jobs.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

public class CreateJob {
    private ObjectId id;
    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String name;
    private boolean active;
    private ObjectId[] competencesId;
    private ObjectId[] activitiesId;


    public CreateJob(String name, boolean active) {
        this.name = name;
        this.active = active;

    }

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
