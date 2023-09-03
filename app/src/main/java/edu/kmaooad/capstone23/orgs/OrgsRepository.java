package edu.kmaooad.capstone23.orgs;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrgsRepository implements PanacheMongoRepository<Org> {

    public Org findByName(String name) {
        return find("name", name).firstResult();
    }

    public Org insert(Org org){
        persist(org);
        return org;
    }
}