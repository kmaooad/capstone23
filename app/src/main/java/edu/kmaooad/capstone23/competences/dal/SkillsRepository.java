package edu.kmaooad.capstone23.competences.dal;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface SkillsRepository {
    Optional<Skill> findByIdOptional(ObjectId id);
    Skill findById(ObjectId id);
    Skill insert(Skill skill);
    List<Skill> findChildRepositories(ObjectId parentSkillId);
    Skill update(Skill skill);
    void deleteSkill(Skill skillToDelete);
}
