package edu.kmaooad.capstone23.orgs;

import java.util.Objects;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "orgs")
public class Org {
    public ObjectId id;
    public String name;
}
