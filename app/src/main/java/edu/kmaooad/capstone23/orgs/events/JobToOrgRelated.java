package edu.kmaooad.capstone23.orgs.events;
public class JobToOrgRelated {

    private String departmentId;
    private String jobId;

    public String getDepartmentId() {
        return departmentId;
    }

    public String getJobId() {
        return jobId;
    }


    public JobToOrgRelated(String departmentId, String jobId) {
        this.departmentId = departmentId;
        this.jobId = jobId;
    }
}

