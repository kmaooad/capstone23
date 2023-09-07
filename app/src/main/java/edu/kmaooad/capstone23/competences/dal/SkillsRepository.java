package edu.kmaooad.capstone23.competences.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SkillsRepository implements PanacheMongoRepository<Skill> {


    public Skill insert(Skill skill) {
        persist(skill);
        return skill;
    }

    public Skill modify(Skill skill) {
        update(skill);
        return skill;
    }
}
