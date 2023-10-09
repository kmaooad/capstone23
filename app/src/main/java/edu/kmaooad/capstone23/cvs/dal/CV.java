package edu.kmaooad.capstone23.cvs.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@MongoEntity(collection = "cvs")
public class CV {

    public ObjectId id;

    public LocalDateTime dateTimeCreated;
    public String textInfo;

    public Status status;

    public Visibility visibility;

    public boolean autoAddCompetences;

    public Set<ObjectId> skills;

    public JobPreference preference;

    public enum Status {
        OPEN,
        CLOSED,
    }

    public enum Visibility {
        VISIBLE,
        HIDDEN,
    }

    public void addSkill(ObjectId skillId) {
        if (this.skills == null) {
            this.skills = new HashSet<>();
        }

        this.skills.add(skillId);
    }

}
