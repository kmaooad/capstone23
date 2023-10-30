package edu.kmaooad.capstone23.experts.dal.dto;

import java.util.List;

public class ExpertRequestDto {
    private String id;
    private String memberId;
    private String name;
    private String orgId;
    private List<String> departmentIds;
    private List<String> projectsIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public List<String> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<String> departmentIds) {
        this.departmentIds = departmentIds;
    }

    public List<String> getProjectsIds() {
        return projectsIds;
    }

    public void setProjectsIds(List<String> projectsIds) {
        this.projectsIds = projectsIds;
    }
}
