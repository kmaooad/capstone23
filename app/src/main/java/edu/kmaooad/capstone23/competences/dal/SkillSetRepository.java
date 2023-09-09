package edu.kmaooad.capstone23.competences.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SkillSetRepository implements PanacheMongoRepository<SkillSet> {

    public SkillSet insert(SkillSet skillSet){
        persist(skillSet);
        return skillSet;
    }
}