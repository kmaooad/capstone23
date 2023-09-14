package edu.kmaooad.capstone23.ban.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.dal.EntityBan;
import edu.kmaooad.capstone23.ban.dal.EntityBanRepository;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class BanEntityHandler implements CommandHandler<BanEntity, EntityBanned> {

    @Inject
    EntityBanRepository entityBanRepository;

    @Inject
    OrgsRepository orgsRepository;


    @Override
    public Result<EntityBanned> handle(BanEntity command) {

        if (BannedEntityType.Organization.text.equals(command.getEntityType())) {
            return banOrg(command);
        } else if (BannedEntityType.User.text.equals(command.getEntityType())) {

        }

        return new Result<>(ErrorCode.EXCEPTION, "Internal error");
    }

    Result<EntityBanned> banOrg(BanEntity command) {
        try {
            var org = orgsRepository.findByIdOptional(command.getEntityId());
            if (org.isEmpty()) {
                return new Result<>(ErrorCode.VALIDATION_FAILED, "Org doesn't exist");
            }
            var previousBan = entityBanRepository.findForEntity(BannedEntityType.Organization, command.getEntityId());
            if (previousBan.isPresent()) {
                return new Result<>(new EntityBanned(previousBan.get().id));
            }
            var newBan = new EntityBan();
            newBan.reason = command.getReason();
            newBan.entityId = command.getEntityId();
            newBan.entityType = BannedEntityType.Organization;
            entityBanRepository.insert(newBan);
            return new Result<>(new EntityBanned(newBan.id));
        } catch (Exception e) {
            return new Result<>(ErrorCode.EXCEPTION, e.getMessage());
        }
    }
}
