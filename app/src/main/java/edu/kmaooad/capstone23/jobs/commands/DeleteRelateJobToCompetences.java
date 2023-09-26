package edu.kmaooad.capstone23.jobs.commands;

import org.bson.types.ObjectId;

public class DeleteRelateJobToCompetences {
    private ObjectId jobId;
    private ObjectId competenceId;

    public DeleteRelateJobToCompetences() {}

    public ObjectId getJobId() {
        return jobId;
    }

    public ObjectId getCompetenceId() {
        return competenceId;
    }

    public void setJobId(ObjectId jobId) {
        this.jobId = jobId;
    }

    public void setCompetenceId(ObjectId competenceId) {
        this.competenceId = competenceId;
    }
}