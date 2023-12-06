package edu.kmaooad.capstone23.competences.dal;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface SkillsRepository {

    Optional<Skill> findById(String id);
    Skill insert(Skill skill);
    List<Skill> findChildRepositories(ObjectId parentSkill);

    Skill modify(Skill skill);


public interface SkillsRepository {
    Optional<Skill> findById(String id);
    Skill insert(Skill skill);
    List<Skill> findChildRepositories(String parentSkill);
    Skill modify(Skill skill);
    void deleteSkill(Skill skillToDelete);
}
