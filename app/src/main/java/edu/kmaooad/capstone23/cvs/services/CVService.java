package edu.kmaooad.capstone23.cvs.services;

import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;


@ApplicationScoped
public class CVService {

    @Inject
    CVRepository cvRepository;


    public CV create(CV cv) {
        cvRepository.persist(cv);
        return cv;
    }


    public void delete(CV cv) {
        cvRepository.delete(cv);
    }


    public CV findById(ObjectId id) {
        return cvRepository.findById(id);
    }


    public PanacheQuery<CV> find(String query, Object... params) {
        return cvRepository.find(query, params);
    }


    public void update(CV cv) {
        cvRepository.update(cv);
    }

    public void deleteById(ObjectId id){
        cvRepository.deleteById(id);
    }

}
