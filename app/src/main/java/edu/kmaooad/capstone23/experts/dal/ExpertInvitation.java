package edu.kmaooad.capstone23.experts.dal;

import edu.kmaooad.capstone23.experts.ExpertType;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.time.LocalDateTime;

@MongoEntity(collection = "expert_invitations")
public class ExpertInvitation extends PanacheMongoEntity {
    public String email;
    public ExpertType expertType;
    public LocalDateTime createdAt;
    public boolean accepted;
}
