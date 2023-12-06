package edu.kmaooad.capstone23.jobs.events;

import org.bson.types.ObjectId;

public class CompetenceUnrelated {
    private final ObjectId jobId;
    private final ObjectId competenceId;

    public CompetenceUnrelated(ObjectId jobId, ObjectId competenceId) {
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