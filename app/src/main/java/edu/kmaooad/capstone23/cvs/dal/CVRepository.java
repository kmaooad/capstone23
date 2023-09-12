package edu.kmaooad.capstone23.cvs.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CVRepository implements PanacheMongoRepository<CV> {

    public CV insert(CV cv) {
        persist(cv);
        return cv;
    }

}
