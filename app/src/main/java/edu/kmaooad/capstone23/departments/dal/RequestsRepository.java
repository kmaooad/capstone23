package edu.kmaooad.capstone23.departments.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;


@ApplicationScoped
public class RequestsRepository implements PanacheMongoRepository<Request> {

    public Request findByUserName(String userName) {
        return find("userName", userName).firstResult();
    }

    public Request findByDepartmentId(String departmentId) {
        return find("departmentId", departmentId).firstResult();
    }

    public Request findById(String id) {
        return findById(new ObjectId(id));
    }


    public Request findAllByStatus(String status) {
        return find("status", status).firstResult();
    }

    public Request changeStatus(String id, String status) {
        Request request = findById(new ObjectId(id));
        request.status = status;
        persist(request);
        return request;
    }

    public Request insert(Request request){
        persist(request);
        return request;
    }
    public void delete(Request request) {
        deleteById(request.id);
    }

}
