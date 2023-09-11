package edu.kmaooad.capstone23.competences.dal;

import java.util.List;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SkillsRepository implements PanacheMongoRepository<Skill> {


    public Skill insert(Skill skill) throws IllegalArgumentException {
        if(skill.parentSkill != null && findByIdOptional(skill.parentSkill).isEmpty())
           throw new IllegalArgumentException("Parent has unknown id");
        persist(skill);
        return skill;
    }

    public List<Skill> findChildRepositories(ObjectId parentSkill) {
        return list("parentSkill = ?1", parentSkill);
    }

    public Skill modify(Skill skill) throws  IllegalArgumentException {
        if(skill.parentSkill != null && findByIdOptional(skill.parentSkill).isEmpty())
            throw new IllegalArgumentException("Parent has unknown id");
        if(skill.id.equals(skill.parentSkill))
            throw new IllegalArgumentException("Parent id and id are equal");
        update(skill);
        return skill;
    }

}
