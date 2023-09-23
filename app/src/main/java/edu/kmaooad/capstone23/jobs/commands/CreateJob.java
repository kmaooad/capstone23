package edu.kmaooad.capstone23.jobs.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class CreateJob {
    private ObjectId id;
    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String name;
    private boolean active;
    private ArrayList<ObjectId> competencesId;
    private ArrayList<ObjectId> activitiesId;


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

    public ArrayList<ObjectId> getCompetencesId() {
        return competencesId;
    }

    public ArrayList<ObjectId> getActivitiesId() {
        return activitiesId;
    }

}
