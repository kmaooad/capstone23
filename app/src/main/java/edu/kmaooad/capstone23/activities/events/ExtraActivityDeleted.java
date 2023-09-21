package edu.kmaooad.capstone23.activities.events;
import org.bson.types.ObjectId;
public class ExtraActivityDeleted {

    private ObjectId cvId;

    public ExtraActivityDeleted(ObjectId cvId) {
        this.cvId = cvId;
    }

    public ObjectId getCVId(){
        return cvId;
    }

}