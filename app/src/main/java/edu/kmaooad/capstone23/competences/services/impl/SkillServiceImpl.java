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
  public Skill insert(Skill skill) throws IllegalArgumentException {
    if (skill.parentSkill != null && repo.findById(skill.parentSkill.toString()).isEmpty()) {
      throw new IllegalArgumentException("Parent has unknown id");
    }
    repo.insert(skill);
    return repo.insert(skill);
  }

  @Override
  public void delete(Skill skill) {
    repo.deleteSkill(skill);
  }

  @Override
  public List<Skill> findChildRepositories(ObjectId parentSkill) {
    return repo.findChildRepositories(parentSkill.toString());
  }

  @Override
  public Skill update(Skill skill) throws IllegalArgumentException {
    if (skill.parentSkill != null && repo.findById(skill.parentSkill.toString()).isEmpty()) {
      throw new IllegalArgumentException("Parent has unknown id");
    }
    if (skill.id.equals(skill.parentSkill)) {
      throw new IllegalArgumentException("Parent id and id are equal");
    }
    repo.modify(skill);
    return skill;
  }

  @Override
  public Optional<Skill> findByIdOptional(ObjectId id) {
    return repo.findById(id.toString());
  }
}
