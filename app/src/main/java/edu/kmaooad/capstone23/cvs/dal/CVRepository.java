package edu.kmaooad.capstone23.cvs.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CVRepository implements PanacheMongoRepository<CV> {

}
