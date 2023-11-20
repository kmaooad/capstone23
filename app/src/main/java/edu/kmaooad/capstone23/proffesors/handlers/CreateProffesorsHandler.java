package edu.kmaooad.capstone23.proffesors.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.proffesors.commands.CreateProffesor;
import edu.kmaooad.capstone23.proffesors.dal.Proffesor;
import edu.kmaooad.capstone23.proffesors.dal.ProffesorsRepository;
import edu.kmaooad.capstone23.proffesors.events.ProffesorCreated;
import edu.kmaooad.capstone23.proffesors.services.ProffesorsService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;


    @RequestScoped
    public class CreateProffesorsHandler implements CommandHandler<CreateProffesor, ProffesorCreated> {

        @Inject
        ProffesorsService proffesorsService;

        @Override
        public Result<ProffesorCreated> handle(CreateProffesor command) {
            Proffesor proffesor = new Proffesor();

            if (command.getName() == null) {
                return new Result<>(ErrorCode.VALIDATION_FAILED, "name is not set");
            }

            if (command.getEmail() == null) {
                return new Result<>(ErrorCode.VALIDATION_FAILED, "email is not set");
            }

            if (command.getLastName() == null) {
                return new Result<>(ErrorCode.VALIDATION_FAILED, "last name is not set");
            }


            proffesor.firstName = command.getName();
            proffesor.lastName = command.getLastName();
            proffesor.email = command.getEmail();
            proffesor.preference = command.getPreference();

            proffesorsService.insert(proffesor);

            ProffesorCreated result = new ProffesorCreated(proffesor.id);
            return new Result<>(result);
        }

    }

