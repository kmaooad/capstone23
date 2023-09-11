package edu.kmaooad.capstone23.departments.events;

public class DepartmentDeleted {
    private String id;

    public String getId() {
        return id;
    }

    public DepartmentDeleted(String departmentId) {
        this.id = departmentId;
    }
}

