package edu.kmaooad.capstone23.competences.dal;

public interface ProjectsRepository {
    Project insert(Project project);
    Project findById(String id);
    void update(Project updatedProject);
    void delete(Project entity);
}
