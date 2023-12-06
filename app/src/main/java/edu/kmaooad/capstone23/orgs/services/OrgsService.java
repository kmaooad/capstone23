package edu.kmaooad.capstone23.orgs.services;

import edu.kmaooad.capstone23.orgs.dal.*;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Optional;

public interface OrgsService {
    Org insert(Org org);
    Org createOrg(String name, String description, String industry, String website, String emailDomain);
    List<Org> bulkInsert(List<Org> orgs);
    long count();
    
    Org findByName(String name);
    Org findById(ObjectId id);
    Org findById(String id);
    Org getByPos(int position);

    Optional<Org> findByEmailDomainOptional(String email);
    Optional<Org> findByIdOptional(String id);

    boolean deleteById(ObjectId id);
    boolean isNotNull();

    void update(Org org);

//Request
    Request insertRequest(Request request);

//Job
    List<Job> findJobsByOrgId(String id);
    void updateJob(Job job);
}