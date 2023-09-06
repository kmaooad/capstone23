package edu.kmaooad.capstone23.jobs.events;

public class JobUpdate {
    private final String jobId;
    public String getJobId() {
        return jobId;
    }

    public JobUpdate(String jobId) {
        this.jobId = jobId;
    }
}
