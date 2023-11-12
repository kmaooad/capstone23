package edu.kmaooad.capstone23.orgs.services;

import edu.kmaooad.capstone23.orgs.dal.Org;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Optional;

public interface OrgsService {
    Org insert(Org org);
    List<Org> bulkInsert(List<Org> orgs);
    long count();
    
    Org findByName(String name);
    Org findById(String id);
    Org getByPos(int position);

    Optional<Org> findByEmailDomainOptional(String email);
    Optional<Org> findByIdOptional(String id);
    Optional<Org> findByEmailOptional(String id);

    boolean deleteById(ObjectId id);
    boolean isNotNull();

}