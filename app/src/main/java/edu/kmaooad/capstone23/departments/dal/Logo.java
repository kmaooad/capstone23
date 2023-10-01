package edu.kmaooad.capstone23.departments.dal;


import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity()
public class Logo {
    public String fileName;
    public String file;
}
