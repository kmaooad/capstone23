package edu.kmaooad.capstone23.ban.service;

import edu.kmaooad.capstone23.ban.commands.IsEntityBannedV2;
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
    public Optional<EntityBan> findForEntity(String type, String entityId) {
        var optionalEntityTpe = getBannedType(type);
        if (optionalEntityTpe.isEmpty())
            return Optional.empty();

        return repository.findForEntity(optionalEntityTpe.get(), new ObjectId(entityId));
    }

    @Override
    public boolean deleteById(String id) {
        return repository.deleteById(new ObjectId(id));
    }


    private Optional<BannedEntityType> getBannedType(String entityType) {
        return switch (entityType) {
            case IsEntityBannedV2.MEMBER_BAN_ENTITY_TYPE -> Optional.of(BannedEntityType.Member);
            case IsEntityBannedV2.ORGANIZATION_BAN_ENTITY_TYPE -> Optional.of(BannedEntityType.Organization);
            case IsEntityBannedV2.DEPARTMENT_BAN_ENTITY_TYPE -> Optional.of(BannedEntityType.Department);
            default -> Optional.empty();
        };
    }
}
