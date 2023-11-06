package edu.kmaooad.capstone23.activities.services;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;

import org.bson.types.ObjectId;

@ApplicationScoped
public class ExtracurricularActivityServiceImpl implements ExtracurricularActivityService {
    @Inject
    private ExtracurricularActivityRepository repo;

    public Optional<ExtracurricularActivity> findByIdOptional(ObjectId id) {
        return repo.findByIdOptional(id);
    }

}
