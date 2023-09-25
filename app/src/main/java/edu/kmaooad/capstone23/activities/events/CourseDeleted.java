package edu.kmaooad.capstone23.activities.events;
import org.bson.types.ObjectId;

public class CourseDeleted {
    private ObjectId course;

    public CourseDeleted(ObjectId course) {
        this.course = course;
    }

    public ObjectId getCourse() {
        return course;
    }
}
