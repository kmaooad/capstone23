package edu.kmaooad.capstone23.ban.service;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.dal.EntityBan;
import edu.kmaooad.capstone23.ban.dal.EntityBanRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class EntityBanServiceImpl implements EntityBanService {

    @Inject
    EntityBanRepository repository;

    @Override
    public EntityBan insert(EntityBan ban) {
        return repository.insert(ban);
    }

    @Override
    public Optional<EntityBan> findForEntity(BannedEntityType type, ObjectId entityId) {
        return repository.findForEntity(type, entityId);
    }

    @Override
    public boolean deleteById(ObjectId id) {
        return repository.deleteById(id);
    }
}
