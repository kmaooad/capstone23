package edu.kmaooad.capstone23.competences.services;

import edu.kmaooad.capstone23.competences.dal.Project;

import java.util.Optional;

public interface ProjectService {

    Project findByName(String name);

    Project insert(Project project);

    Project findById(String id);

    void update(Project project);

    void delete(Project project);

    Optional<Project> findByIdOptional(String id);
}
