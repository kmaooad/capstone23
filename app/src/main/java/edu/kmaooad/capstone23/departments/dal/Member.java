package edu.kmaooad.capstone23.departments.dal;


import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity()
public class Member {
    public String userName;
    public String role;
}
