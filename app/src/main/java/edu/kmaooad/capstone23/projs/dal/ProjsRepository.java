package edu.kmaooad.capstone23.projs.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProjsRepository implements PanacheMongoRepository<Proj> {

    public Proj findByName(String name) {
        return find("name", name).firstResult();
    }

    public Proj insert(Proj proj) {
        persist(proj);
        return proj;
    }
}
