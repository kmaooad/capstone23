package edu.kmaooad.capstone23.proffesors.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.proffesors.commands.AddProfessorToGroup;
import edu.kmaooad.capstone23.proffesors.dal.Proffesor;
import edu.kmaooad.capstone23.proffesors.dal.ProffesorsRepository;
import edu.kmaooad.capstone23.proffesors.events.ProfessorAddedToGroup;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class AddProfessorToGroupHandler implements CommandHandler<AddProfessorToGroup, ProfessorAddedToGroup> {

    @Inject
    ProffesorsRepository proffesorsRepository;

    @Override
    public Result<ProfessorAddedToGroup> handle(AddProfessorToGroup command) {
        Optional<Proffesor> maybeProfessor = proffesorsRepository.findByIdOptional(command.getProfessorId());

        maybeProfessor.get().getGroups().add(command.getGroupId());

        proffesorsRepository.update(maybeProfessor.get());

        return new Result<>(new ProfessorAddedToGroup(command.getProfessorId(), command.getGroupId()));
    }
}
