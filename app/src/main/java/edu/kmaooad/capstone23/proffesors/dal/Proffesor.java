package edu.kmaooad.capstone23.proffesors.dal;


import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.util.Set;

@MongoEntity(collection = "proffesors")
public class Proffesor {
    public ObjectId id;
    public String firstName;

    public JobPreference getPreference() {
        return preference;
    }

    public void setPreference(JobPreference preference) {
        this.preference = preference;
    }

    public JobPreference preference;


    public String lastName;

    public String email;
}

