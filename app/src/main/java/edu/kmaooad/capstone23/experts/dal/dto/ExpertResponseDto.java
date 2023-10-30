package edu.kmaooad.capstone23.experts.dal.dto;

import edu.kmaooad.capstone23.competences.dal.Project;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.orgs.dal.Org;
import java.util.List;
import org.bson.types.ObjectId;

public class ExpertResponseDto {
    private ObjectId id;
    private ObjectId memberId;
    private String name;
    private Org org;
    private List<Department> departments;
    private List<Project> projects;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getMemberId() {
        return memberId;
    }

    public void setMemberId(ObjectId memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
