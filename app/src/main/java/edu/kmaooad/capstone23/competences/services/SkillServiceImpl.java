package edu.kmaooad.capstone23.competences.services;

import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public class SkillServiceImpl implements SkillService {
    @Inject
    private SkillsRepository skillsRepository;

    @Override
    public Optional<Skill> findById(ObjectId id) {
        return skillsRepository.findById(id.toString());
    }

    @Override
    public Skill insert(Skill skill) throws IllegalArgumentException {
        if (skill.parentSkill != null && skillsRepository.findById(skill.parentSkill.toString()).isEmpty()) {
            throw new IllegalArgumentException("Parent has unknown id");
        }
        skillsRepository.insert(skill);
        return skillsRepository.insert(skill);
    }

    @Override
    public Optional<Skill> findByIdOptional(ObjectId id) {
        return skillsRepository.findById(id.toString());
    }

    @Override
    public void delete(Skill skill) {
        skillsRepository.deleteSkill(skill);
    }

    @Override
    public List<Skill> findChildRepositories(ObjectId parentSkill) {
        return skillsRepository.findChildRepositories(parentSkill.toString());
    }

    @Override
    public Skill update(Skill skill) throws IllegalArgumentException {
        if (skill.parentSkill != null && skillsRepository.findById(skill.parentSkill.toString()).isEmpty()) {
            throw new IllegalArgumentException("Parent has unknown id");
        }
        if (skill.id.equals(skill.parentSkill)) {
            throw new IllegalArgumentException("Parent id and id are equal");
        }
        skillsRepository.modify(skill);
        return skill;
    }
}
