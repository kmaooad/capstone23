package edu.kmaooad.capstone23.ban.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.dal.EntityBanRepository;
import edu.kmaooad.capstone23.ban.events.EntityUnbanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UnbanEntityHandler implements CommandHandler<BanEntity, EntityUnbanned> {

    @Inject
    EntityBanRepository entityBanRepository;

    @Inject
    OrgsRepository orgsRepository;

    @Override
    public Result<EntityUnbanned> handle(BanEntity command) {
        if (BannedEntityType.Organization.toString().equals(command.getEntityTypeName())) {
            return unbanOrg(command);
        } else if (BannedEntityType.User.toString().equals(command.getEntityTypeName())) {
            
        }

        return new Result<>(ErrorCode.VALIDATION_FAILED, "Invalid entity type");
    }

    Result<EntityUnbanned> unbanOrg(BanEntity command) {
        try {
            var org = orgsRepository.findByIdOptional(command.getEntityId());
            if (org.isEmpty()) {
                return new Result<>(ErrorCode.VALIDATION_FAILED, "Org doesn't exist");
            }
            var ban = entityBanRepository.findForEntity(BannedEntityType.Organization, command.getEntityId());
            if (ban.isEmpty()) {
                return new Result<>(new EntityUnbanned(null, BannedEntityType.Organization));
            }

            entityBanRepository.deleteById(ban.get().id);
            return new Result<>(new EntityUnbanned(ban.get().id, BannedEntityType.Organization));
    
        } catch (Exception e) {
            return new Result<>(ErrorCode.EXCEPTION, e.getMessage());
        }
    }
}
