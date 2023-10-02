package edu.kmaooad.capstone23.departments.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class RelateJobToDepartment {
    @NotBlank
    @Size(min = 2, max = 50)
    private String departmentId;

    @NotBlank
    private String jobId;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}