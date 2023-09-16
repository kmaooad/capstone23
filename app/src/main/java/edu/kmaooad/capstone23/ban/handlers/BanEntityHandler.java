package edu.kmaooad.capstone23.ban.handlers;

import org.bson.types.ObjectId;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.dal.EntityBan;
import edu.kmaooad.capstone23.ban.dal.EntityBanRepository;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class BanEntityHandler implements CommandHandler<BanEntity, EntityBanned> {

    @Inject
    EntityBanRepository entityBanRepository;

    @Inject
    OrgsRepository orgsRepository;

    @Inject
    DepartmentsRepository departmentsRepository;

    @Inject
    MembersRepository membersRepository;

    @Override
    public Result<EntityBanned> handle(BanEntity command) {
        try {
            BannedEntityType.valueOf(command.getEntityTypeName());
        } catch (Exception e) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, String.format("Invalid entity type: %s", command.getEntityTypeName()));
        }

        return banEntity(command);
    }

    boolean entityExists(BannedEntityType type, ObjectId entityId) {
        switch (type) {
            case Organization:
                return orgsRepository.findByIdOptional(entityId).isPresent();
            case Member:
                return membersRepository.findByIdOptional(entityId).isPresent();
            case Department:
                return departmentsRepository.findByIdOptional(entityId).isPresent();
            default:
                throw new IllegalArgumentException("Invalid entity type");
        }
    }

    Result<EntityBanned> banEntity(BanEntity command) {
        try {
            var entityType = command.getEntityType();

            if (!entityExists(entityType, command.getEntityId())) {
                return new Result<>(ErrorCode.VALIDATION_FAILED, "Entity doesn't exist");
            }

            var previousBan = entityBanRepository.findForEntity(entityType, command.getEntityId());
            if (previousBan.isPresent()) {
                return new Result<>(new EntityBanned(previousBan.get().id, entityType));
            }

            var newBan = new EntityBan();
            newBan.reason = command.getReason();
            newBan.entityId = command.getEntityId();
            newBan.entityType = entityType;
            entityBanRepository.insert(newBan);
            return new Result<>(new EntityBanned(newBan.id, entityType));
        } catch (Exception e) {
            return new Result<>(ErrorCode.EXCEPTION, e.getMessage());
        }
    }
}
