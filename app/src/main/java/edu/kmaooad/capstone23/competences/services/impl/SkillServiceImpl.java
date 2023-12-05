package edu.kmaooad.capstone23.competences.services.impl;

import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import edu.kmaooad.capstone23.competences.services.SkillService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;
import org.bson.types.ObjectId;

@ApplicationScoped
public class SkillServiceImpl implements SkillService {
  @Inject
  private SkillsRepository repo;

  @Override
  public Optional<Skill> findByIdOptional(ObjectId id) {
    return repo.findByIdOptional(id);
  }
}
