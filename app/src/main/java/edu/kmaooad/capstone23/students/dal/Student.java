package edu.kmaooad.capstone23.students.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "students")
public class Student {
    public ObjectId id;
    public String firstName;
    public String middleName;
    public String lastName;
    public long DOBTimestamp;
    public String email;
}
