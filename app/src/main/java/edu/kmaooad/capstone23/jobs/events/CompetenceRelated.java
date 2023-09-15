package edu.kmaooad.capstone23.jobs.events;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class CompetenceRelated {

    private final ObjectId jobId;
    private final ArrayList<ObjectId> competenceId;

    public CompetenceRelated(ObjectId jobId, ArrayList<ObjectId> competenceId) {
        this.jobId = jobId;
        this.competenceId = competenceId;
    }

    public ObjectId getJobId() {
        return jobId;
    }

    public ArrayList<ObjectId> getCompetencesId() {
        return competenceId;
    }
}