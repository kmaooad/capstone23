package edu.kmaooad.capstone23.jobs.events;

public class JobCreated {

    private String jobId;

    private String jobDescription;



    public String getJobId() {
        return jobId;
    }

    public String getJobDescription() {return jobDescription;}


    public JobCreated(String jobId) {
        this.jobId = jobId;
    }

    public JobCreated(String jobId, String jobDescription) {
        this.jobId = jobId;
        this.jobDescription = jobDescription;
    }

}
