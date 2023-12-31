package edu.kmaooad.capstone23.departments.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;


@ApplicationScoped
public class RequestsRepository implements PanacheMongoRepository<Request> {
    public Request findById(String id) {
        return findById(new ObjectId(id));
    }


    public Request insert(Request request){
        persist(request);
        return request;
    }
    public void delete(Request request) {
        deleteById(request.id);
    }

}
