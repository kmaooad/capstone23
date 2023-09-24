package edu.kmaooad.capstone23.orgs.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.Optional;

import java.util.List;

@ApplicationScoped
public class OrgsRepository implements PanacheMongoRepository<Org> {

    public Org findByName(String name) {
        return find("name", name).firstResult();
    }

    public Org findById(String id) {
        return find("id", id).firstResult();
    }

    public Org findByEmailDomain(String id) {
        return find("emailDomain", id).firstResult();
    }

    public Optional<Org> findByIdOptional(String id) {
        if (!ObjectId.isValid(id)) {
            return Optional.empty();
        }
        return findByIdOptional(new ObjectId(id));
    }

    public Org insert(Org org){
        persist(org);
        return org;
    }

    public List<Org> bulkInsert(List<Org> ogrs) {
        persist(ogrs.stream());
        return ogrs;
    }
}
