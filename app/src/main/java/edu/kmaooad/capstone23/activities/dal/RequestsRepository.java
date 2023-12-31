package edu.kmaooad.capstone23.activities.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;


@ApplicationScoped
public class RequestsRepository implements PanacheMongoRepository<Request> {
    public Request findById(String id) {
        return findById(new ObjectId(id));
    }
    public Request insert(Request request){
        persist(request);
        return request;
    }

    public List<Request> findListByUserNameAndExtraActId(String userName, String extraActId) {
        return list("userName = ?1 and extraActId = ?2", userName, extraActId);
    }
}
