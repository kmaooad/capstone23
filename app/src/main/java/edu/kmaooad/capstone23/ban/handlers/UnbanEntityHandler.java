package edu.kmaooad.capstone23.ban.handlers;

import org.bson.types.ObjectId;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.commands.UnbanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.dal.EntityBan;
import edu.kmaooad.capstone23.ban.dal.EntityBanRepository;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.ban.events.EntityUnbanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UnbanEntityHandler implements CommandHandler<UnbanEntity, EntityUnbanned> {

    @Inject
    EntityBanRepository entityBanRepository;

    @Inject
    OrgsRepository orgsRepository;

    @Inject
    DepartmentsRepository departmentsRepository;

    @Inject
    MembersRepository membersRepository;

    @Override
    public Result<EntityUnbanned> handle(UnbanEntity command) {
        try {
            BannedEntityType.valueOf(command.getEntityTypeName());
        } catch (Exception e) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, String.format("Invalid entity type: %s", command.getEntityTypeName()));
        }

        return unbanEntity(command);
    }

    Result<EntityUnbanned> unbanEntity(UnbanEntity command) {
        try {
            var entityType = command.getEntityType();

            var ban = entityBanRepository.findForEntity(entityType, command.getEntityId());
            if (ban.isPresent()) {
                entityBanRepository.deleteById(ban.get().id);
                return new Result<>(new EntityUnbanned(ban.get().id, entityType));
            } else {
                return new Result<>(new EntityUnbanned(null, entityType));
            }
        } catch (Exception e) {
            return new Result<>(ErrorCode.EXCEPTION, e.getMessage());
        }
    }
}
