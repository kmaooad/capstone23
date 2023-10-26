package edu.kmaooad.capstone23.group_templates.services;

import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplatesRepository;
import edu.kmaooad.capstone23.groups.dal.Group;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class GroupTemplatesServiceImpl implements GroupTemplatesService {

    @Inject
    GroupTemplatesRepository repository;

    @Override
    public GroupTemplate insert(GroupTemplate groupTemplate) {
        return repository.insert(groupTemplate);
    }

    @Override
    public List<Group> findChildGroups(String objectId) {
        return repository.findChildGroups(objectId);
    }

    @Override
    public void delete(GroupTemplate groupTemplate) {
        repository.delete(groupTemplate);
    }

    @Override
    public void update(GroupTemplate groupTemplate) {
        repository.update(groupTemplate);
    }

    @Override
    public GroupTemplate findById(ObjectId objectId) {
        return repository.findById(objectId);
    }
}
