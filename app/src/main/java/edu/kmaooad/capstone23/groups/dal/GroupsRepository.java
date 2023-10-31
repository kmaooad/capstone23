package edu.kmaooad.capstone23.groups.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GroupsRepository implements PanacheMongoRepository<Group> {

    public Group findByName(String name) {
        return find("name", name).firstResult();
    }

    public Group insert(Group group) {
        persist(group);
        return group;
    }
}
