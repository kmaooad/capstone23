package edu.kmaooad.capstone23.relations.commands;

import jakarta.validation.constraints.*;
import org.bson.types.ObjectId;

public class SetRelation {

    @NotNull
    @NotBlank
    public String collectionName1;

    @NotNull
    @NotBlank
    public String collectionName2;

    @NotNull
    public ObjectId objectToConnectId1;

    @NotNull
    public ObjectId objectToConnectId2;
}
