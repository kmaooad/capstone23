package edu.kmaooad.capstone23.experts.events;

public class ExpertRemovedFromDepartment {
    private String departmentId;

    public String getDepartmentId() {
        return departmentId;
    }

    public ExpertRemovedFromDepartment(String departmentId) {
        this.departmentId = departmentId;
    }
}
