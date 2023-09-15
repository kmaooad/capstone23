package edu.kmaooad.capstone23.jobs.commands;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class RelateJobToCompetences {
    private ObjectId jobId;
    private ObjectId competenceId;

    public RelateJobToCompetences() {}

    public RelateJobToCompetences(ObjectId jobId, ObjectId competenceId) {
        this.jobId = jobId;
        this.competenceId = competenceId;
    }

    public ObjectId getJobId() {
        return jobId;
    }

    public ObjectId getCompetenceId() {
        return competenceId;
    }

    public void setJobId(ObjectId jobId) {
        this.jobId = jobId;
    }

    public void setCompetencesId(ObjectId competenceId) {
        this.competenceId = competenceId;
    }
}
