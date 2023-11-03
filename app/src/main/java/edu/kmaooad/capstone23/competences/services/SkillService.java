package edu.kmaooad.capstone23.competences.services;

import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.jobs.dal.Job;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface SkillService {
    Optional<Skill> findById(ObjectId id);
    Skill insert(Skill skill);
}
