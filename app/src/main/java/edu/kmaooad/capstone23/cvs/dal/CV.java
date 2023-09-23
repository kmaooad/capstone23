package edu.kmaooad.capstone23.cvs.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@MongoEntity(collection = "cvs")
public class CV {

    public ObjectId id;
//    public Student student;

    public LocalDateTime dateTimeCreated;
    public String textInfo;

    public Status status;
    public Visibility visibility;

    public boolean autoAddCompetences;
//    public List<Competence> competencesId;

    public JobPreference preference;

    public enum Status {
        OPEN,
        CLOSED,
    }

    public enum Visibility {
        VISIBLE,
        HIDDEN,
    }

}
