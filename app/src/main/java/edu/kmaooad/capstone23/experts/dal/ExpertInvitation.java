package edu.kmaooad.capstone23.experts.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import java.time.LocalDateTime;

@MongoEntity(collection = "expert_invitations")
public class ExpertInvitation extends PanacheMongoEntity {
    public String email;
    public String message;
    public LocalDateTime createdAt;
    public boolean accepted;
}
