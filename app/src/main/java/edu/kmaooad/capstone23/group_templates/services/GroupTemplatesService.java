package edu.kmaooad.capstone23.group_templates.services;

import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.groups.dal.Group;

import java.util.List;

public interface GroupTemplatesService {
    GroupTemplate insert(GroupTemplate groupTemplate);
    List<Group> findChildGroups(String objectId);
    void delete(GroupTemplate groupTemplate);
    void update(GroupTemplate groupTemplate);
}
