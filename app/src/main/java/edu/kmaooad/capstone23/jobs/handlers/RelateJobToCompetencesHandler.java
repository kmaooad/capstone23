package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToActivities;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToCompetences;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.jobs.events.ActivityRelated;
import edu.kmaooad.capstone23.jobs.events.CompetenceRelated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class RelateJobToCompetencesHandler implements CommandHandler<RelateJobToCompetences, CompetenceRelated> {
    @Inject
    private JobRepository repository;

    @Override
    public Result<CompetenceRelated> handle(RelateJobToCompetences command) {

        Optional<Job> job = repository.findByIdOptional(command.getJobId());
        if(job.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This job was previously deleted or never existed");

        CompetenceRelated result = new CompetenceRelated(command.getJobId(), command.getCompetencesId());

        Job j = job.get();
        j.activitiesId.addAll(command.getCompetencesId());

        repository.update(j);

        return new Result<CompetenceRelated>(result);
    }
}
