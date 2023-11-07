package edu.kmaooad.capstone23.cvs.services;

import edu.kmaooad.capstone23.cvs.dal.CV;
import io.quarkus.mongodb.panache.PanacheQuery;
import org.bson.types.ObjectId;

public interface CVService {

    CV create(CV cv);

    void delete(CV cv);

    CV findById(ObjectId id);

    PanacheQuery<CV> find(String query, Object... params);

    void update(CV cv);

}
