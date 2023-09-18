package edu.kmaooad.capstone23.cvs.commands;

import edu.kmaooad.capstone23.cvs.dal.CV;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

import java.util.Set;

public class SetStatus
{

    @NotNull
    private ObjectId cvId;

    private CV.Status status;

    public ObjectId getCvId() {
        return cvId;
    }

    public void setCvId(ObjectId cvId) {
        this.cvId = cvId;
    }

    public CV.Status getStatus() {
        return status;
    }

    public void setStatus(CV.Status status) {
        this.status = status;
    }

}
