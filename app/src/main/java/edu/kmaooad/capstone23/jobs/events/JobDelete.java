package edu.kmaooad.capstone23.jobs.events;

public class JobDelete {
    private final String jobId;
    public String getJobId() {
        return jobId;
    }

    public JobDelete(String jobId) {
        this.jobId = jobId;
    }
}
