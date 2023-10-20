package edu.kmaooad.capstone23.ban.handlers;

import edu.kmaooad.capstone23.ban.commands.IsEntityBanned;
import edu.kmaooad.capstone23.ban.commands.IsEntityBannedV2;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.events.EntityIsBanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@RequestScoped
public class IsBannedV2Handler implements CommandHandler<IsEntityBannedV2, EntityIsBanned> {

    @Inject
    CommandHandler<IsEntityBanned, EntityIsBanned> isBannedHandler;

    @Override
    public Result<EntityIsBanned> handle(IsEntityBannedV2 command) {
        var entityType = getBannedType(command.getEntityType());
        if(entityType.isEmpty())
            return new Result<>(new EntityIsBanned(true));

        var entityId = new ObjectId(command.getEntityId());
        return isBannedHandler.handle(new IsEntityBanned(entityId, entityType.get().name()));
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
