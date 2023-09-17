package edu.kmaooad.capstone23.groups.dal;
import edu.kmaooad.capstone23.orgs.dal.Org;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GroupsRepository implements PanacheMongoRepository<Group> {

    public Group findByName(String name) {
        return find("name", name).firstResult();
    }

    public Group insert(Group group){
        persist(group);
        return group;
    }
}
