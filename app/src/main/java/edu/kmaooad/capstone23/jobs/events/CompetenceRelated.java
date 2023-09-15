package edu.kmaooad.capstone23.jobs.events;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class CompetenceRelated {

    private final ObjectId jobId;
    private final ObjectId competenceId;

    public CompetenceRelated(ObjectId jobId, ObjectId competenceId) {
        this.jobId = jobId;
        this.competenceId = competenceId;
    }

    public ObjectId getJobId() {
        return jobId;
    }

    public ObjectId getCompetenceId() {
        return competenceId;
    }
}