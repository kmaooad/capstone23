package edu.kmaooad.capstone23.ban.service;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.dal.EntityBan;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface EntityBanService {
    EntityBan insert(EntityBan ban);
    Optional<EntityBan> findForEntity(BannedEntityType type, ObjectId entityId);
}


