package edu.kmaooad.capstone23.proffesors.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.proffesors.commands.AssignActivity;
import edu.kmaooad.capstone23.proffesors.commands.UnassignActivity;
import edu.kmaooad.capstone23.proffesors.dal.Proffesor;
import edu.kmaooad.capstone23.proffesors.dal.ProffesorsRepository;
import edu.kmaooad.capstone23.proffesors.events.ActivityAssigned;
import edu.kmaooad.capstone23.proffesors.events.ActivityUnassigned;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class UnassignActivityHandler implements CommandHandler<UnassignActivity, ActivityUnassigned> {

    @Inject
    ProffesorsRepository proffesorsRepository;

    @Override
    public Result<ActivityUnassigned> handle(UnassignActivity command) {
        Optional<Proffesor> proffesor = proffesorsRepository.findByIdOptional(command.getProfessor());

        if (proffesor.isEmpty()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Professor does not exist");
        }

        proffesor.get().activities.remove(command.getActivity());
        proffesorsRepository.update(proffesor.get());

        ActivityUnassigned result = new ActivityUnassigned();
        result.setActivityId(command.getActivity());
        result.setProfessorId(command.getProfessor());

        return new Result<>(result);
    }

}
