package edu.kmaooad.capstone23.competences.services;

import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import jakarta.inject.Inject;

import java.util.Optional;

public class SkillSetServiceImpl implements SkillSetService {
    @Inject
    private SkillSetRepository skillSetRepository;

    @Override
    public Optional<SkillSet> findById(String id) {
        return skillSetRepository.findById(id);
    }

    @Override
    public SkillSet insert(SkillSet skillSet) {
        return skillSetRepository.insert(skillSet);
    }
}
