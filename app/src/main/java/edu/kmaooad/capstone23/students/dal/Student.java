package edu.kmaooad.capstone23.students.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import edu.kmaooad.capstone23.activities.dal.Activity;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

@MongoEntity(collection = "students")
public class Student {
    public ObjectId objectId;
    private String name;
    private List<Activity> assignedActivities;

    public Student(String name) {
        this.name = name;
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

    public String getName() {
        return name;
    }
}
