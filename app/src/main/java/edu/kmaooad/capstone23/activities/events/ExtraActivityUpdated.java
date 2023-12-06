package edu.kmaooad.capstone23.activities.events;
import org.bson.types.ObjectId;
public class ExtraActivityUpdated {

    private ObjectId cvId;

    public ExtraActivityUpdated(ObjectId cvId) {
        this.cvId = cvId;
    }

    public ObjectId getCVId(){
        return cvId;
    }

}