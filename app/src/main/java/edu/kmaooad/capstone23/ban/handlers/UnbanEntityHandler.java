package edu.kmaooad.capstone23.ban.handlers;

import edu.kmaooad.capstone23.ban.commands.UnbanEntity;
import edu.kmaooad.capstone23.ban.events.EntityUnbanned;
import edu.kmaooad.capstone23.ban.service.EntityBanService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UnbanEntityHandler implements CommandHandler<UnbanEntity, EntityUnbanned> {

    @Inject
    EntityBanService entityBanService;

    @Override
    public Result<EntityUnbanned> handle(UnbanEntity command) {
        var entityType = command.getEntityType();

        var ban = entityBanService.findForEntity(entityType.name(), command.getEntityId().toString());
        if (ban.isPresent()) {
            entityBanService.deleteById(ban.get().id.toString());
            return new Result<>(new EntityUnbanned(ban.get().id, entityType));
        } else {
            return new Result<>(new EntityUnbanned(null, entityType));
        }

    }
}
