package edu.kmaooad.capstone23.activities.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.util.Date;

@MongoEntity(collection = "extracurricularActivity")
public class ExtracurricularActivity {

    public ObjectId id;

    public String extracurricularActivityName;
    public Date extracurricularActivityDate;
}