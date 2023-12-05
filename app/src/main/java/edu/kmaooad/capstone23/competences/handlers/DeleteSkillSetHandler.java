package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.DeleteSkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.events.SkillSetDeleted;
import edu.kmaooad.capstone23.competences.services.SkillSetService;
import edu.kmaooad.capstone23.notifications.models.Event;
import edu.kmaooad.capstone23.notifications.service.NotificationService;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@RequestScoped
public class DeleteSkillSetHandler implements CommandHandler<DeleteSkillSet, SkillSetDeleted> {


    @Inject
    private SkillSetService service;

    @Inject
    private UserRepository userRepository;

    @Inject
    private NotificationService notificationService;


    @Override
    public Result<SkillSetDeleted> handle(DeleteSkillSet command) {
        ObjectId id = command.getId();
        SkillSet skillSet = service.findById(id);

        if (skillSet == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Skill set not found");
        }

        service.delete(skillSet);


        List<User> users = userRepository.listAll();
        for (User user : users) {
            if (user.notificationEvents.contains(Event.SKILL_SET_DELETED))   {
                notificationService.sendMessage(user.id.toHexString(), "Skill set deleted " + skillSet.id.toHexString());
            }
        }

        return new Result<>(new SkillSetDeleted(skillSet));
    }
}
