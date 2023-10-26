package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.DeleteRelateJobToCompetences;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.jobs.events.CompetenceUnrelated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;
@RequestScoped
public class DeleteRelationJobToCompetencesHandler  implements CommandHandler<DeleteRelateJobToCompetences, CompetenceUnrelated> {

    @Inject
    private JobRepository repository;
    @Override
    public Result<CompetenceUnrelated> handle(DeleteRelateJobToCompetences command) {

        Optional<Job> job = repository.findByIdOptional(command.getJobId());
        if(job.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This job was previously deleted or never existed");

        CompetenceUnrelated result = new CompetenceUnrelated(command.getJobId(), command.getCompetenceId());

        Job j = job.get();
        if (!j.competencesId.contains(command.getCompetenceId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This job doesn't contain this competence");
        j.competencesId.remove(command.getCompetenceId());
        repository.update(j);

        return new Result<CompetenceUnrelated>(result);
    }
}