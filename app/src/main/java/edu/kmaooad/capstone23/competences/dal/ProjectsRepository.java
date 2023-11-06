package edu.kmaooad.capstone23.competences.dal;

import java.util.Optional;
import org.bson.types.ObjectId;

public interface ProjectsRepository {
    Optional<Project> findByIdMaybe(ObjectId id);
    Project insertProject(Project project);
    Project findProjectById(String id);
    void updateProject(Project updatedProject);
    void deleteProject(Project entity);
}
