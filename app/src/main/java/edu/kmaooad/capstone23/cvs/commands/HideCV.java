package edu.kmaooad.capstone23.cvs.commands;
import edu.kmaooad.capstone23.cvs.dal.CV;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

import java.util.Set;

public class HideCV {
    @NotNull
    private ObjectId cvId;

    public ObjectId getCvId() {
        return cvId;
    }

    public void setCvId(ObjectId cvId) {
        this.cvId = cvId;
    }

}
