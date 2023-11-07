package edu.kmaooad.capstone23.students.events;

import org.bson.types.ObjectId;

public class StudentDeleted {
    private ObjectId objectId;

    public StudentDeleted(ObjectId objectId) {
        this.objectId = objectId;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }
}
