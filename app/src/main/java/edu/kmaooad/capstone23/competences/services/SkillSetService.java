package edu.kmaooad.capstone23.competences.services;

import edu.kmaooad.capstone23.competences.dal.SkillSet;

import java.util.Optional;

public interface SkillSetService {
    public Optional<SkillSet> findById(String id);

    public SkillSet insert(SkillSet skillSet);
}
