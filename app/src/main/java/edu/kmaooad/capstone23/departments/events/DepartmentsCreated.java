package edu.kmaooad.capstone23.departments.events;

public class DepartmentsCreated {
    private String departmentId;

    public String getDepartmentId() {
        return departmentId;
    }

    public DepartmentsCreated(String departmentId) {
        this.departmentId = departmentId;
    }
}
