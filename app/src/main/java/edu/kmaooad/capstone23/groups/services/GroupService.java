package edu.kmaooad.capstone23.groups.services;

import edu.kmaooad.capstone23.groups.dal.Group;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface GroupService {

    Group findByName(String name);
    Group insert(Group group);
    Group findById(String id);
    void update(Group group);
    void delete(Group group);
    Optional<Group> findByIdOptional(String id);
}
