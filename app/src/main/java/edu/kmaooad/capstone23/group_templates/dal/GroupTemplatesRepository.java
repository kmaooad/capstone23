package edu.kmaooad.capstone23.group_templates.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GroupTemplatesRepository implements PanacheMongoRepository<GroupTemplate> {

    public GroupTemplate findByName(String name) {
        return find("name", name).firstResult();
    }

    public GroupTemplate insert(GroupTemplate groupTemplate){
        persist(groupTemplate);
        return groupTemplate;
    }
}
