package edu.kmaooad.capstone23.proffesors.dal;


import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ProffesorsRepository implements PanacheMongoRepository<Proffesor> {
    public Proffesor insert(Proffesor pr) {
        persist(pr);
        return pr;
    }
}

