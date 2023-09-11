package edu.kmaooad.capstone23.experts.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@MongoEntity(collection = "expert_invitations")
public class ExpertInvitation extends PanacheMongoEntity {
    public String email;
    public LocalDateTime createdAt;
    public boolean accepted;
}
