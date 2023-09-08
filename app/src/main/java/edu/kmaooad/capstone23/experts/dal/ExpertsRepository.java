package edu.kmaooad.capstone23.experts.dal;

import edu.kmaooad.capstone23.orgs.dal.Org;
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
}