package edu.kmaooad.capstone23.activities.events;
import org.bson.types.ObjectId;
public class ExtraActivityCreated {
    private final ObjectId id;

    public ExtraActivityCreated(ObjectId id) {
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }
}
