package edu.kmaooad.capstone23.jobs.events;

public class JobUpdated {
    private String jobId;
    public String getJobId() {
        return jobId;
    }

    public JobUpdated(String jobId) {
        this.jobId = jobId;
    }
}
