package edu.kmaooad.capstone23.cvs.services;

import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class CVServiceImpl implements CVService {

    @Inject
    CVRepository cvRepository;


    @Override
    public CV create(CV cv) {
        cvRepository.persist(cv);
        return cv;
    }


    @Override
    public void delete(CV cv) {
        cvRepository.delete(cv);
    }


    @Override
    public CV findById(ObjectId id) {
        return cvRepository.findById(id);
    }

    @Override
    public void deleteById(ObjectId id) {
        cvRepository.deleteById(id);
    }

    @Override
    public PanacheQuery<CV> find(String query, Object... params) {
        return cvRepository.find(query, params);
    }

    @Override
    public void update(CV cv) {
        cvRepository.update(cv);
    }

    @Override
    public Optional<CV> findByIdOptional(ObjectId id) {
        return cvRepository.findByIdOptional(id);
    }
}
