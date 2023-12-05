package edu.kmaooad.capstone23.proffesors.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.models.NotificationType;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import edu.kmaooad.capstone23.proffesors.commands.AssignActivity;
import edu.kmaooad.capstone23.proffesors.dal.Proffesor;
import edu.kmaooad.capstone23.proffesors.dal.ProffesorsRepository;
import edu.kmaooad.capstone23.proffesors.events.ActivityAssigned;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.Optional;

@RequestScoped
public class AssignActivityHandler
    implements CommandHandler<AssignActivity, ActivityAssigned> {

  @Inject ProffesorsRepository proffesorsRepository;

  @Inject NotificationService notificationService;

  @Override
  public Result<ActivityAssigned> handle(AssignActivity command) {
    Optional<Proffesor> proffesor =
        proffesorsRepository.findByIdOptional(command.getProfessor());

    if (proffesor.isEmpty()) {
      return new Result<>(ErrorCode.VALIDATION_FAILED,
                          "Professor does not exist");
    }

    proffesor.get().activities.add(command.getActivity());
    proffesorsRepository.update(proffesor.get());

    ActivityAssigned result = new ActivityAssigned();
    result.setActivityId(command.getActivity());
    result.setProfessorId(command.getProfessor());

    notificationService.send(
        NotificationType.PROFESSOR_ACTIVITY_ASSIGNED, command.getProfessor(),
        "Activity " + command.getActivity() + " assigned to you");

    return new Result<ActivityAssigned>(result);
  }
}
