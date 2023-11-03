package edu.kmaooad.capstone23.competences.services;

import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

public class SkillServiceImpl implements SkillService{
    @Inject
    private SkillsRepository skillsRepository;

    @Override
    public Optional<Skill> findById(ObjectId id) {
        return skillsRepository.findByIdOptional(id);
    }

    @Override
    public Skill insert(Skill skill) {
        return skillsRepository.insert(skill);
    }
}