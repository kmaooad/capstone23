package edu.kmaooad.capstone23.experts.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ExpertsRepository implements PanacheMongoRepository<Expert> {

    public Expert findByName(String name) {
        return find("name", name).firstResult();
    }
    public List<Expert> findByOrg(String org) {
        return find("org", org).list();
    }

    public Expert insert(Expert expert){
        persist(expert);
        return expert;
    }

    public void deleteExpert(Expert expert){
        delete(expert);
    }

    public void updateExpert(Expert expert){
        update(expert);
    }

    public Expert modify(Expert expert) throws IllegalArgumentException {
        if (expert != null && findByIdOptional(expert.id).isEmpty())
            throw new IllegalArgumentException("Expert has unknown id");
        update(expert);
        return expert;
    }
}