package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.DeleteSkill;
import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import edu.kmaooad.capstone23.competences.events.SkillDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class DeleteSkillHandler implements CommandHandler<DeleteSkill, SkillDeleted> {


    @Inject
    private SkillsRepository repository;


    @Override
    public Result<SkillDeleted> handle(DeleteSkill command) {
        Skill skill = repository.findById(command.getId());

        System.out.println(skill);


        repository.delete(skill);

        return new Result<>(new SkillDeleted(skill));
    }
}
