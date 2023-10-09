package edu.kmaooad.capstone23.activities.dal;

import edu.kmaooad.capstone23.tag.dal.Tag;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@MongoEntity(collection = "extracurricularActivity")
public class ExtracurricularActivity {

    public ObjectId id;

    public String extracurricularActivityName;
    public Date extracurricularActivityDate;
    public List<ObjectId> skillIds;

    public List<Tag> tags;
}
