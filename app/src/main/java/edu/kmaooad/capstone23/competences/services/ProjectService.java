package edu.kmaooad.capstone23.competences.services;

import edu.kmaooad.capstone23.competences.dal.Project;
import org.bson.types.ObjectId;
import java.util.Optional;

public interface ProjectService {
  public Optional<Project> findByIdOptional(ObjectId id);

}
