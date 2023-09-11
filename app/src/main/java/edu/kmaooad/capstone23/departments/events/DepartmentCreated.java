package edu.kmaooad.capstone23.departments.events;

public class DepartmentCreated {
    private String id;
    public String getId() {
        return id;
    }

    public DepartmentCreated(String departmentId) {
        this.id = departmentId;
    }
}
