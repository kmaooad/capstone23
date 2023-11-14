package edu.kmaooad.capstone23.ban.service;

import edu.kmaooad.capstone23.ban.dal.EntityBan;

import java.util.Optional;

public interface EntityBanService {
    EntityBan insert(EntityBan ban);
    Optional<EntityBan> findForEntity(String type, String entityId);

    boolean deleteById(String id);
}


