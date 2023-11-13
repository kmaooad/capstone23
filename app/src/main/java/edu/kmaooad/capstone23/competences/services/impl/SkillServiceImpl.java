package edu.kmaooad.capstone23.competences.services.impl;

import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import edu.kmaooad.capstone23.competences.services.SkillService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

@ApplicationScoped
public class SkillServiceImpl implements SkillService {
  @Inject
  private SkillsRepository repo;

  @Override
  public Optional<Skill> findById(ObjectId id) {
    return repo.findById(id.toString());
  }

  @Override
  public Skill insert(Skill skill) {
    return null;
  }

  @Override
  public void delete(Skill skill) {

  }

  @Override
  public List<Skill> findChildRepositories(ObjectId parentSkill) {
    return null;
  }

  @Override
  public Skill update(Skill skill) {
    return repo.modify(skill);
  }

  @Override
  public Optional<Skill> findByIdOptional(ObjectId id) {
    return repo.findById(id.toString());
  }
}
