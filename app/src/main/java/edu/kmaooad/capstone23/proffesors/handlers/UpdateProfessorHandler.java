package edu.kmaooad.capstone23.proffesors.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.proffesors.commands.UpdateProfessor;
import edu.kmaooad.capstone23.proffesors.dal.Proffesor;
import edu.kmaooad.capstone23.proffesors.dal.ProffesorsRepository;
import edu.kmaooad.capstone23.proffesors.events.ProfessorUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class UpdateProfessorHandler implements CommandHandler<UpdateProfessor, ProfessorUpdated> {

    @Inject
    ProffesorsRepository proffesorsRepository;

    @Override
    public Result<ProfessorUpdated> handle(UpdateProfessor command) {

        Optional<Proffesor> professorToBeUpdated = proffesorsRepository.findByIdOptional(command.getProfessorId());

        // TODO write validations for each field

        professorToBeUpdated.get().firstName = command.getFirstName();
        professorToBeUpdated.get().lastName = command.getLastName();
        professorToBeUpdated.get().email = command.getEmail();
        professorToBeUpdated.get().setPreference(command.getPreference());

        proffesorsRepository.update(professorToBeUpdated.get());

        return new Result<>(new ProfessorUpdated(command.getProfessorId()));
    }
}
