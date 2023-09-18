package edu.kmaooad.capstone23.orgs.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class OrgsRepository implements PanacheMongoRepository<Org> {

    public Org findByName(String name) {
        return find("name", name).firstResult();
    }

    public Optional<Org> findById(String id) {
        if (!ObjectId.isValid(id)) {
            return Optional.empty();
        }
        return findByIdOptional(new ObjectId(id));
    }

    public Org insert(Org org){
        persist(org);
        return org;
    }
}