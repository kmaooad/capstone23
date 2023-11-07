package edu.kmaooad.capstone23.competences.services;

import edu.kmaooad.capstone23.competences.dal.Skill;
import org.bson.types.ObjectId;
import java.util.Optional;

public interface SkillService {
  public Optional<Skill> findByIdOptional(ObjectId id);
}

