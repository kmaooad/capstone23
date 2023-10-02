package edu.kmaooad.capstone23.departments.events;

public class JobToDepartmentRelated {

    private String departmentId;
    private String jobId;
    public String getDepartmentId() {
        return departmentId;
    }

    public String getJobId() {
        return jobId;
    }


    public JobToDepartmentRelated(String departmentId, String jobId) {
        this.departmentId = departmentId;
        this.jobId = jobId;
    }
}
