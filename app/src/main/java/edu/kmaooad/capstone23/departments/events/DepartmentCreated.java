package edu.kmaooad.capstone23.departments.events;

public class DepartmentCreated {
    private String departmentId;
    public String getDepartmentId() {
        return departmentId;
    }

    public DepartmentCreated(String departmentId) {
        this.departmentId = departmentId;
    }
}
