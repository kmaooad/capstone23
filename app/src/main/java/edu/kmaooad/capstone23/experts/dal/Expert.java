package edu.kmaooad.capstone23.experts.dal;

import edu.kmaooad.capstone23.orgs.dal.*;
import io.quarkus.mongodb.panache.common.*;
import org.bson.types.*;

@MongoEntity(collection = "experts")
public class Expert {
    public ObjectId id;
    private String name;
    private Org org;
}
