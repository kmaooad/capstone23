package edu.kmaooad.capstone23.jobs.events;

public class JobCreated {
    private String jobId;
    public String getJobId() {
        return jobId;
    }

    public JobCreated(String jobId) {
        this.jobId = jobId;
    }
}
