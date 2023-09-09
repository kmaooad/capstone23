package edu.kmaooad.capstone23.orgs.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SkillSetRepository implements PanacheMongoRepository<Org> {

    public SkillSet insert(SkillSet skillSet){
        persist(skillSet);
        return skillSet;
    }
}