package edu.kmaooad.capstone23.ban.handlers;

import edu.kmaooad.capstone23.ban.service.EntityBanService;
import org.bson.types.ObjectId;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.dal.EntityBan;
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
    EntityBanService entityBanService;

    @Inject
    OrgsRepository orgsRepository;

    @Inject
    DepartmentsRepository departmentsRepository;

    @Inject
    MembersRepository membersRepository;

    @Override
    public Result<EntityBanned> handle(BanEntity command) {
        var entityType = command.getEntityType();

        if (!entityExists(entityType, command.getEntityId())) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Entity doesn't exist");
        }

        var previousBan = entityBanService.findForEntity(entityType.name(), command.getEntityId().toString());
        if (previousBan.isPresent()) {
            return new Result<>(new EntityBanned(previousBan.get().id, entityType));
        }

        var newBan = new EntityBan();
        newBan.reason = command.getReason();
        newBan.entityId = command.getEntityId();
        newBan.entityType = entityType;
        entityBanService.insert(newBan);
        return new Result<>(new EntityBanned(newBan.id, entityType));
    }

    boolean entityExists(BannedEntityType type, ObjectId entityId) {
        return switch (type) {
            case Organization -> orgsRepository.findByIdOptional(entityId).isPresent();
            case Member -> membersRepository.findByIdOptional(entityId).isPresent();
            case Department -> departmentsRepository.findByIdOptional(entityId).isPresent();
        };
    }
}
