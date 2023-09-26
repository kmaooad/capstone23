package edu.kmaooad.capstone23.relations.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

public class UnsetRelation {
    @NotNull
    private ObjectId id;

    @NotNull
    @NotBlank
    private String collectionName1;

    @NotNull
    @NotBlank
    private String collectionName2;

    public UnsetRelation(ObjectId id, String firstCollectionName, String secondCollectionName) {
        this.id = id;
        this.collectionName1 = firstCollectionName;
        this.collectionName2 = secondCollectionName;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCollectionName1() {
        return collectionName1;
    }

    public String getCollectionName2() {
        return collectionName2;
    }
}
