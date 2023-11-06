package edu.kmaooad.capstone23.competences.services.impl;

import edu.kmaooad.capstone23.competences.dal.ProjectsRepository;
import edu.kmaooad.capstone23.competences.services.ProjectService;
import jakarta.inject.Inject;
import java.util.Optional;

import edu.kmaooad.capstone23.competences.dal.Project;
import org.bson.types.ObjectId;

public class ProjectServiceImpl implements ProjectService {
  @Inject
  private ProjectsRepository repo;

  @Override
  public Optional<Project> findByIdOptional(ObjectId id) {
    return repo.findByIdOptional(id);
  }
}
