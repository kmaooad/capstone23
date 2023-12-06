package edu.kmaooad.capstone23.proffesors.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.proffesors.commands.DeleteProfessor;
import edu.kmaooad.capstone23.proffesors.dal.ProffesorsRepository;
import edu.kmaooad.capstone23.proffesors.events.ProfessorDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class DeleteProfessorHandler implements CommandHandler<DeleteProfessor, ProfessorDeleted> {

    @Inject
    ProffesorsRepository professorRepository;

    @Override
    public Result<ProfessorDeleted> handle(DeleteProfessor command) {
        boolean professorExisted = professorRepository.deleteById(command.getProfessorId());
        return new Result<>(new ProfessorDeleted(command.getProfessorId(), professorExisted));
    }
}
