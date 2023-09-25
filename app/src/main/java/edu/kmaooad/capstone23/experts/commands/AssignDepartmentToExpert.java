package edu.kmaooad.capstone23.experts.commands;

import jakarta.validation.constraints.NotNull;

public class AssignDepartmentToExpert {
    @NotNull
    private String expertId;
    @NotNull
    private String departmentId;

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}
