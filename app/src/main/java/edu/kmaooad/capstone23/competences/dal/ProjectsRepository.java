package edu.kmaooad.capstone23.competences.dal;

import java.util.Optional;

import edu.kmaooad.capstone23.search.Listable;
import org.bson.types.ObjectId;

public interface ProjectsRepository extends Listable<Project> {
    Optional<Project> findByIdMaybe(ObjectId id);
    Project insertProject(Project project);
    Project findProjectById(String id);
    void updateProject(Project updatedProject);
    void deleteProject(Project entity);
}
