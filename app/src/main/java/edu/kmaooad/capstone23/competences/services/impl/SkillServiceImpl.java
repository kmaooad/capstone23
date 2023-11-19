package edu.kmaooad.capstone23.competences.services.impl;

import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import edu.kmaooad.capstone23.competences.services.SkillService;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Optional;

public class SkillServiceImpl implements SkillService{
    @Inject
    private SkillsRepository skillsRepository;

    @Override
    public Skill findById(String id) {
        return skillsRepository.findById(id);
    }

    @Override
    public Skill insert(Skill skill) throws IllegalArgumentException {
        if(skill.parentSkill != null && skillsRepository.findByIdOptional(skill.parentSkill.toString()).isEmpty())
            throw new IllegalArgumentException("Parent has unknown id");
        return skillsRepository.insert(skill);
    }

    @Override
    public Optional<Skill> findByIdOptional(String id) {
        return skillsRepository.findByIdOptional(id);
    }

    @Override
    public void delete(Skill skill) {
        skillsRepository.deleteSkill(skill);
    }

    @Override
    public List<Skill> findChildRepositories(ObjectId parentSkill) {
        return skillsRepository.findChildRepositories(parentSkill);
    }

    @Override
    public Skill update(Skill skill) throws  IllegalArgumentException {
        if(skill.parentSkill != null && skillsRepository.findByIdOptional(skill.parentSkill.toString()).isEmpty())
            throw new IllegalArgumentException("Parent has unknown id");
        if(skill.id.equals(skill.parentSkill))
            throw new IllegalArgumentException("Parent id and id are equal");
        skillsRepository.modify(skill);
        return skill;
    }
}
