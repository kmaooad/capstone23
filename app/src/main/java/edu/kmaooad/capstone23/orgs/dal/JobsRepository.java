package edu.kmaooad.capstone23.orgs.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class JobsRepository implements PanacheMongoRepository<Job> {

    public List<Job> findByOrgId(String id) {
        ObjectId objectId = new ObjectId(id);
        return find("orgId", objectId).list();
    }
}
