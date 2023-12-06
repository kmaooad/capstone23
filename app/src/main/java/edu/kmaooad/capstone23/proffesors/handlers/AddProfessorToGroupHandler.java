package edu.kmaooad.capstone23.proffesors.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
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

    @Inject
    GroupsRepository groupRepositiory;

    @Override
    public Result<ProfessorAddedToGroup> handle(AddProfessorToGroup command) {
        Optional<Proffesor> maybeProfessor = proffesorsRepository.findByIdOptional(command.getProfessorId());
        Optional<Group> maybeGroup = groupRepositiory.findByIdOptional(command.getGroupId());

        if (maybeProfessor.isEmpty()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Professor does not exist");
        } else if (maybeGroup.isEmpty()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Group does not exist");
        }

        maybeProfessor.get().getGroups().add(command.getGroupId());

        proffesorsRepository.update(maybeProfessor.get());

        return new Result<>(new ProfessorAddedToGroup(command.getProfessorId(), command.getGroupId()));
    }
}
