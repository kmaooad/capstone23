package edu.kmaooad.capstone23.relations.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

public class UnsetRelation {
    @NotNull
    private ObjectId id;

    @NotNull
    @NotBlank
    private final String firstCollectionName;

    @NotNull
    @NotBlank
    private final String secondCollectionName;

    public UnsetRelation(ObjectId id, String firstCollectionName, String secondCollectionName) {
        this.id = id;
        this.firstCollectionName = firstCollectionName;
        this.secondCollectionName = secondCollectionName;
    }

    public ObjectId getId() {
        return id;
    }

    public String getFirstCollectionName() {
        return firstCollectionName;
    }

    public String getSecondCollectionName() {
        return secondCollectionName;
    }
}
