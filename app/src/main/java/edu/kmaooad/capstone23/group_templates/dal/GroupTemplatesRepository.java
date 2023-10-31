package edu.kmaooad.capstone23.group_templates.dal;

import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.interfaces.GroupsRepositoryInterface;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GroupTemplatesRepository implements PanacheMongoRepository<GroupTemplate> {

    @Inject
    private GroupsRepositoryInterface groupsRepository;

    public GroupTemplate findByName(String name) {
        return find("name", name).firstResult();
    }

    public GroupTemplate insert(GroupTemplate groupTemplate) {
        persist(groupTemplate);
        return groupTemplate;
    }

    public List<Group> findChildGroups(String objectId) {
        return groupsRepository.list("templateId = ?1", objectId);
    }
}
