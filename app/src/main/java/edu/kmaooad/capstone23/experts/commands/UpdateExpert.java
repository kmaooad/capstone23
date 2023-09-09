package edu.kmaooad.capstone23.experts.commands;

import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;

public class UpdateExpert {

    @NotBlank
    private String expertId;

    public ObjectId getExpertId() {
        return new ObjectId(expertId);
    }
}
