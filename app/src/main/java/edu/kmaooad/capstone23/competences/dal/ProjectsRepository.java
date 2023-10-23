package edu.kmaooad.capstone23.competences.dal;

public interface ProjectsRepository {
    Project insertProject(Project project);
    Project findProjectById(String id);
    void updateProject(Project updatedProject);
    void deleteProject(Project entity);
}
