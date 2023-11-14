package edu.kmaooad.capstone23.students.commands;

import org.bson.types.ObjectId;

public class DeleteStudent {
    private ObjectId objectId;

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }
}
