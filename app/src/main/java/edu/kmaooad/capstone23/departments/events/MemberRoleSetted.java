package edu.kmaooad.capstone23.departments.events;

public class MemberRoleSetted {

    private String departmentId;
    private String userName;
    private String role;
    public String getDepartmentId() {
        return departmentId;
    }

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }

    public MemberRoleSetted(String departmentId, String userName, String role) {
        this.departmentId = departmentId;
        this.userName = userName;
        this.role = role;
    }
}
