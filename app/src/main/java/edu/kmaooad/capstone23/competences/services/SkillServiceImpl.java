package edu.kmaooad.capstone23.competences.services;

import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public class SkillServiceImpl implements SkillService{
    @Inject
    private SkillsRepository skillsRepository;

    @Override
    public Optional<Skill> findById(ObjectId id) {
        return skillsRepository.findByIdOptional(id);
    }

    @Override
    public Skill insert(Skill skill) throws IllegalArgumentException {
        if(skill.parentSkill != null && skillsRepository.findByIdOptional(skill.parentSkill).isEmpty())
            throw new IllegalArgumentException("Parent has unknown id");
        skillsRepository.persist(skill);
        return skillsRepository.insert(skill);
    }

    @Override
    public void delete(Skill skill) {
        skillsRepository.delete(skill);
    }

    @Override
    public List<Skill> findChildRepositories(ObjectId parentSkill) {
        return skillsRepository.findChildRepositories(parentSkill);
    }

    @Override
    public Skill update(Skill skill) throws  IllegalArgumentException {
        if(skill.parentSkill != null && skillsRepository.findByIdOptional(skill.parentSkill).isEmpty())
            throw new IllegalArgumentException("Parent has unknown id");
        if(skill.id.equals(skill.parentSkill))
            throw new IllegalArgumentException("Parent id and id are equal");
        skillsRepository.update(skill);
        return skill;
    }
}
