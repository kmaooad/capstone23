package edu.kmaooad.capstone23.experts.dal;

import edu.kmaooad.capstone23.experts.ExpertType;
import edu.kmaooad.capstone23.orgs.dal.Org;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.time.LocalDateTime;

@MongoEntity(collection = "expert_invitations")
public class ExpertInvitation extends PanacheMongoEntity {
    public String email;
    public ExpertType expertType;
    public LocalDateTime createdAt;
    public String expertName;
    public Org org;
    public boolean accepted;
}
