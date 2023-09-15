package edu.kmaooad.capstone23.orgs.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrgsRepository implements PanacheMongoRepository<Org> {

    public Org findByName(String name) {
        return find("name", name).firstResult();
    }
    public Org findById(String id) {
        return find("id", id).firstResult();
    }

    public Org insert(Org org){
        persist(org);
        return org;
    }
}