package edu.kmaooad.capstone23.jobs.events;

public class JobDeleted {
    private String jobId;
    public String getJobId() {
        return jobId;
    }

    public JobDeleted(String jobId) {
        this.jobId = jobId;
    }
}
