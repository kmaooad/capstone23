package edu.kmaooad.capstone23.jobs.commands;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class RelateToCompetences {
    private ObjectId jobId;
    private ArrayList<ObjectId> competencesId;

    public RelateToCompetences() {}

    public RelateToCompetences(ObjectId jobId, ObjectId competenceId) {
        this.jobId = jobId;
        this.competencesId = new ArrayList<>();
        this.competencesId.add(competenceId);
    }

    public RelateToCompetences(ObjectId jobId, ArrayList<ObjectId> competencesId) {
        this.jobId = jobId;
        this.competencesId = new ArrayList<>();
        this.competencesId.addAll(competencesId);
    }

    public ObjectId getJobId() {
        return jobId;
    }

    public ArrayList<ObjectId> getCompetencesId() {
        return competencesId;
    }

    public void setJobId(ObjectId jobId) {
        this.jobId = jobId;
    }

    public void setCompetencesId(ArrayList<ObjectId> competencesId) {
        this.competencesId = competencesId;
    }
}
