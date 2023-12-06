package edu.kmaooad.capstone23.competences.services;

import edu.kmaooad.capstone23.competences.dal.SkillSet;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface SkillSetService {
    public Optional<SkillSet> findById(String id);

    public SkillSet insert(SkillSet skillSet);

    public void update (SkillSet skillSet);
    public void delete (SkillSet skillSet);
    public SkillSet findById(ObjectId id);

}
