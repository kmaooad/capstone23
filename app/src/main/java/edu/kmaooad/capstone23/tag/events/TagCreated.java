package edu.kmaooad.capstone23.tag.events;

import org.bson.types.ObjectId;

public class TagCreated {
    ObjectId objectId;
    String tagName;

    public TagCreated(ObjectId objectId, String tagName) {
        this.objectId = objectId;
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public ObjectId getObjectId() {
        return objectId;
    }
}
