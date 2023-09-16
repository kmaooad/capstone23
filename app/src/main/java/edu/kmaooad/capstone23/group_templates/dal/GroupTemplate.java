package edu.kmaooad.capstone23.group_templates.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import edu.kmaooad.capstone23.activities.dal.Activity;

@MongoEntity(collection = "grouptemplates")
public class GroupTemplate {
    public ObjectId id;
    public String name;
    private List<Activity> assignedActivities;

    public GroupTemplate() {
        this.name = "group";
        this.assignedActivities = new ArrayList<>();
    }

    public void assignActivity(Activity activity) {
        assignedActivities.add(activity);
    }

    public void unassignActivity(Activity activity) {
        assignedActivities.remove(activity);
    }

    public List<Activity> getAssignedActivities() {
        return assignedActivities;
    }

}
