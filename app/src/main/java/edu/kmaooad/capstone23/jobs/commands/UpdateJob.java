package edu.kmaooad.capstone23.jobs.commands;

import org.bson.types.ObjectId;

import java.util.Objects;

public class UpdateJob {
    private ObjectId jobId;

    public UpdateJob(ObjectId jobId) {
        this.jobId = jobId;
    }

    public ObjectId getJobId() {
        return jobId;
    }

    public void setJobId(ObjectId jobId) {
        this.jobId = jobId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateJob deleteJob = (UpdateJob) o;
        return jobId.equals(deleteJob.jobId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId);
    }
}
