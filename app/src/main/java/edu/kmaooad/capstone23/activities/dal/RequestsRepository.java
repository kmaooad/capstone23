package edu.kmaooad.capstone23.activities.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class RequestsRepository implements PanacheMongoRepository<Request> {
    public Request insert(Request request){
        persist(request);
        return request;
    }

}
