package edu.kmaooad.capstone23.ban.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class EntityBanRepository implements PanacheMongoRepository<EntityBan> {

    public EntityBan insert(EntityBan ban) {
        persist(ban);
        return ban;
    }

    public Optional<EntityBan> findForEntity(BannedEntityType type, ObjectId entityId) {
        var result = find("entityType = :entityType and entityId = :entityId", Parameters.with("entityType", type).and("entityId", entityId));
        return result.firstResultOptional();
    }
}
