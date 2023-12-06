package edu.kmaooad.capstone23.proffesors.dal;


import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.constraints.Email;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.Set;

@MongoEntity(collection = "proffesors")
public class Proffesor {
    public ObjectId id;

    public String firstName;
    public String lastName;

    @Email
    public String email;

    public JobPreference preference;

    public Set<ObjectId> activities;
    private Set<ObjectId> groups;

    public Proffesor() {
        this.activities = new HashSet<>(1);
        this.groups = new HashSet<>(1);
    }

    public JobPreference getPreference() {
        return preference;
    }

    public void setPreference(JobPreference preference) {
        this.preference = preference;
    }

    public Set<ObjectId> getGroups() {
        return groups;
    }
}

