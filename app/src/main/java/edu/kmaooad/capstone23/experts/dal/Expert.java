package edu.kmaooad.capstone23.experts.dal;

import edu.kmaooad.capstone23.orgs.dal.Org;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "experts")
public class Expert {
    public ObjectId id;
    public String name;
    public Org org;
}
