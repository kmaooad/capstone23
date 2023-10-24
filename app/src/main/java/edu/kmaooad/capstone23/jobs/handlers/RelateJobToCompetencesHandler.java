package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.dal.*;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToCompetences;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.jobs.events.CompetenceRelated;
import edu.kmaooad.capstone23.jobs.service.JobService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class RelateJobToCompetencesHandler implements CommandHandler<RelateJobToCompetences, CompetenceRelated> {
    @Inject
    private JobService jobService;

    @Inject
    private ProjsRepository projsRepository;
    
    @Inject
    private SkillsRepository skillsRepository;
    
    @Inject
    private TopicRepository topicRepository;

    @Override
    public Result<CompetenceRelated> handle(RelateJobToCompetences command) {

        Optional<Job> job = jobService.findJobById(command.getJobId());
        if(job.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This job was previously deleted or never existed");

        Optional<Project> project = projsRepository.findByIdOptional(command.getCompetenceId());
        Optional<Skill> skill = skillsRepository.findByIdOptional(command.getCompetenceId());
        Optional<Topic> topic = topicRepository.findByIdOptional(command.getCompetenceId());
        if(project.isEmpty() && skill.isEmpty() && topic.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This competence was previously deleted or never existed");

        CompetenceRelated result = new CompetenceRelated(command.getJobId(), command.getCompetenceId());

        Job j = job.get();
        j.competencesId.add(command.getCompetenceId());

        jobService.update(j);

        return new Result<CompetenceRelated>(result);
    }
}
