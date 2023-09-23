package edu.kmaooad.capstone23.jobs.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

import java.util.List;

public class CreateJob {
    private ObjectId id;
    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String name;
    private boolean active;

    private List<ObjectId> competencesId;
    private List<ObjectId> activitiesId;


    public CreateJob(String name, boolean active) {
        this.name = name;
        this.active = active;

        this.competencesId = new ArrayList<>();
        this.activitiesId = new ArrayList<>();
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

    public List<ObjectId> getCompetencesId() {
        return competencesId;
    }

    public List<ObjectId> getActivitiesId() {
        return activitiesId;
    }

}
