package edu.kmaooad.capstone23.ban.handlers;

import edu.kmaooad.capstone23.ban.commands.IsEntityBanned;
import edu.kmaooad.capstone23.ban.commands.IsEntityBannedV2;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.events.EntityIsBanned;
import edu.kmaooad.capstone23.ban.events.EntityIsBannedV2;
import edu.kmaooad.capstone23.ban.service.EntityBanService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@RequestScoped
public class IsBannedV2Handler implements CommandHandler<IsEntityBannedV2, EntityIsBannedV2> {

    @Inject
    EntityBanService isBannedHandler;

    @Override
    public Result<EntityIsBannedV2> handle(IsEntityBannedV2 command) {
        var ban = isBannedHandler.findForEntity(command.getEntityType(), command.getEntityId());

        return new Result<>(new EntityIsBannedV2(ban.isPresent()));
    }
}
