package edu.kmaooad.capstone23.departments.events;

public class LogoAdded {

    private String departmentId;

    public String getDepartmentId() {
        return departmentId;
    }

    public LogoAdded(String departmentId) {
        this.departmentId = departmentId;
    }

}
