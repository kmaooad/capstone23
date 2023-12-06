package edu.kmaooad.capstone23.proffesors.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.service.NotificationType;
import edu.kmaooad.capstone23.proffesors.commands.CreateProffesor;
import edu.kmaooad.capstone23.proffesors.dal.Proffesor;
import edu.kmaooad.capstone23.proffesors.dal.ProffesorsRepository;
import edu.kmaooad.capstone23.proffesors.events.ProffesorCreated;
import edu.kmaooad.capstone23.proffesors.notifications.create.CreateProfessorNotificationService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;


    @RequestScoped
    public class CreateProffesorsHandler implements CommandHandler<CreateProffesor, ProffesorCreated> {

        @Inject
        ProffesorsRepository cvRepository;

        @Inject
        CreateProfessorNotificationService notificationService;

        @Override
        public Result<ProffesorCreated> handle(CreateProffesor command) {
            Proffesor cv = new Proffesor();

            if (command.getName() == null) {
                return new Result<>(ErrorCode.VALIDATION_FAILED, "name is not set");
            }

            if (command.getEmail() == null) {
                return new Result<>(ErrorCode.VALIDATION_FAILED, "email is not set");
            }

            if (command.getLastName() == null) {
                return new Result<>(ErrorCode.VALIDATION_FAILED, "last name is not set");
            }


            cv.firstName = command.getName();
            cv.lastName = command.getLastName();
            cv.email = command.getEmail();
            cv.preference = command.getPreference();

            cvRepository.insert(cv);

            ProffesorCreated result = new ProffesorCreated(cv.id);
            notificationService.send(NotificationType.SMS, result);

            return new Result<>(result);
        }

    }

