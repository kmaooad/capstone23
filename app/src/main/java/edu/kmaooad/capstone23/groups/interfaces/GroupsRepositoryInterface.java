package edu.kmaooad.capstone23.groups.interfaces;

import edu.kmaooad.capstone23.groups.dal.Group;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface GroupsRepositoryInterface extends PanacheMongoRepository<Group> {

    Group findByName(String name);

    Group insert(Group group);
}
