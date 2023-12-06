package edu.kmaooad.capstone23.jobs.service;

import edu.kmaooad.capstone23.competences.dal.Project;
import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.services.ProjectService;
import edu.kmaooad.capstone23.competences.services.SkillService;
import edu.kmaooad.capstone23.competences.services.TopicService;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JobServiceImpl implements JobService{
    @Inject
    private JobRepository jobRepository;

    @Inject
    private SkillService skillService;

    @Inject
    private ProjectService projectService;

    @Inject
    private TopicService topicService;


    @Override
    public Optional<Job> findJobById(String id) {
        return jobRepository.findByIdOptional(new ObjectId(id));
    }

    @Override
    public List<Job> listAll() {
        return jobRepository.listAll();
    }

    @Override
    public long count() {
        return jobRepository.count();
    }

    @Override
    public Job insert(Job job) {
        return jobRepository.insert(job);
    }
    @Override
    public void delete(Job job) {
        jobRepository.delete(job);
    }

    @Override
    public void update(Job job) {
        jobRepository.update(job);
    }

    public Boolean isJobRelatedToCompetence(String competenceId) {
        var competenceObjId = new ObjectId(competenceId);

        Optional<Project> project = projectService.findByIdOptional(competenceObjId);
        Optional<Skill> skill = skillService.findById(competenceObjId);
        Optional<Topic> topic = topicService.findByIdOptional(competenceObjId);

        return project.isEmpty() && skill.isEmpty() && topic.isEmpty();
    }
}
