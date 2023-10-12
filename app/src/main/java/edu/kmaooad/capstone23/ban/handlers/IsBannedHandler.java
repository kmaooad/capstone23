package edu.kmaooad.capstone23.ban.handlers;

import edu.kmaooad.capstone23.ban.commands.IsEntityBanned;
import edu.kmaooad.capstone23.ban.dal.EntityBanRepository;
import edu.kmaooad.capstone23.ban.events.EntityIsBanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class IsBannedHandler implements CommandHandler<IsEntityBanned, EntityIsBanned> {

    @Inject
    EntityBanRepository repository;

    @Override
    public Result<EntityIsBanned> handle(IsEntityBanned command) {
        var isBanned = repository.findForEntity(command.getEntityType(), command.getEntityId()).isPresent();
        return new Result<>(new EntityIsBanned(isBanned));
    }
}
