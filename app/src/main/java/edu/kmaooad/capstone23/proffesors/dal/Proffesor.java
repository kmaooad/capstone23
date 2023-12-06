package edu.kmaooad.capstone23.proffesors.dal;


import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.Set;

@MongoEntity(collection = "proffesors")
public class Proffesor {
    public ObjectId id;
    public String firstName;

    public Set<ObjectId> activities;

    public JobPreference preference;


    public String lastName;

    public String email;

    public Proffesor() {
        this.activities = new HashSet<>();
    }

    public JobPreference getPreference() {
        return preference;
    }

    public void setPreference(JobPreference preference) {
        this.preference = preference;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public ObjectId getId() {
        return id;
    }
}

