package edu.kmaooad.capstone23.experts.dal;

import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.orgs.dal.Org;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.util.List;

@MongoEntity(collection = "experts")
public class Expert {
    public ObjectId id;
    public ObjectId memberId;
    public String name;
    public Org org;
    public List<Department> department;
}
