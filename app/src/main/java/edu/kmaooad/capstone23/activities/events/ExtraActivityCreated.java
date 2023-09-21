package edu.kmaooad.capstone23.activities.events;
import org.bson.types.ObjectId;
public class ExtraActivityCreated {

    private ObjectId cvId;

    public ExtraActivityCreated(ObjectId cvId) {
        this.cvId = cvId;
    }

    public ObjectId getCVId(){
        return cvId;
    }

}