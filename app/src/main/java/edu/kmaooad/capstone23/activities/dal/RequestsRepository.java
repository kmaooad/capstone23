package edu.kmaooad.capstone23.activities.dal;

import edu.kmaooad.capstone23.orgs.dal.Request;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.bson.types.ObjectId;

public class RequestsRepository implements PanacheMongoRepository<edu.kmaooad.capstone23.orgs.dal.Request> {
    public edu.kmaooad.capstone23.orgs.dal.Request findById(String id) {
        return findById(new ObjectId(id));
    }
    public edu.kmaooad.capstone23.orgs.dal.Request insert(edu.kmaooad.capstone23.orgs.dal.Request request){
        persist(request);
        return request;
    }
    public void delete(Request request) {
        deleteById(request.id);
    }
}