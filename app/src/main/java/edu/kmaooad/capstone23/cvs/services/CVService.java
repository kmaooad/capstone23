package edu.kmaooad.capstone23.cvs.services;

import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;
public interface CVService {

    CV create(CV cv);

    void delete(CV cv);


    CV findById(ObjectId id);

    void deleteById(ObjectId id);
    PanacheQuery<CV> find(String query, Object... params);

    void update(CV cv);

    Optional<CV> findByIdOptional(ObjectId id);
}
