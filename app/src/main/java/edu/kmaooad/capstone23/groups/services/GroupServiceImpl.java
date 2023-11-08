package edu.kmaooad.capstone23.groups.services;

import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class GroupServiceImpl implements GroupService{

    @Inject
    private GroupsRepository repository;

    @Override
    public Group findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Group insert(Group group) {
        return repository.insert(group);
    }

    @Override
    public Group findById(String id) {
        return repository.findById(new ObjectId(id));
    }

    @Override
    public void update(Group group) {
        repository.update(group);
    }

    @Override
    public void delete(Group group) {
        repository.delete(group);
    }

    @Override
    public Optional<Group> findByIdOptional(String id) {
        return repository.findByIdOptional(new ObjectId(id));
    }
}
