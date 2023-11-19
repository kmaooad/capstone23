package edu.kmaooad.capstone23.competences.services;

import edu.kmaooad.capstone23.competences.dal.Skill;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    Skill findById(ObjectId id);
    Skill insert(Skill skill);
    void delete(Skill skill);
    List<Skill> findChildRepositories(ObjectId parentSkill);
    Skill update(Skill skill);
    public Optional<Skill> findByIdOptional(ObjectId id);
}


