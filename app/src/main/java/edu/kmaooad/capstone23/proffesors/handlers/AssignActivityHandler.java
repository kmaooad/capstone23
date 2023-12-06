package edu.kmaooad.capstone23.proffesors.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.proffesors.commands.AssignActivity;
import edu.kmaooad.capstone23.proffesors.dal.Proffesor;
import edu.kmaooad.capstone23.proffesors.dal.ProffesorsRepository;
import edu.kmaooad.capstone23.proffesors.events.ActivityAssigned;
import edu.kmaooad.capstone23.proffesors.services.ProffesorsService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class AssignActivityHandler implements CommandHandler<AssignActivity, ActivityAssigned> {

    @Inject
    ProffesorsService proffesorsService;

    @Override
    public Result<ActivityAssigned> handle(AssignActivity command) {
        Optional<Proffesor> proffesor = proffesorsService.findByIdOptional(command.getProfessor());

        if (proffesor.isEmpty()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Professor does not exist");
        }

        proffesor.get().activities.add(command.getActivity());
        proffesorsService.update(proffesor.get());

        ActivityAssigned result = new ActivityAssigned();
        result.setActivityId(command.getActivity());
        result.setProfessorId(command.getProfessor());

        return new Result<ActivityAssigned>(result);
    }
}
