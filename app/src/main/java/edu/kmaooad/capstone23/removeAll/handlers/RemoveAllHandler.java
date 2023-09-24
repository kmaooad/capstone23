package edu.kmaooad.capstone23.removeAll.handlers;

import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.ban.dal.EntityBanRepository;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.dal.ProjsRepository;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import edu.kmaooad.capstone23.competences.dal.TopicRepository;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.experts.dal.ExpertInvitationRepository;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplatesRepository;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.dal.RequestsRepository;
import edu.kmaooad.capstone23.removeAll.commands.RemoveAll;
import edu.kmaooad.capstone23.removeAll.events.AllRemoved;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class RemoveAllHandler implements CommandHandler<RemoveAll, AllRemoved> {

    @Inject
    private OrgsRepository orgsRepository;

    @Inject
    private DepartmentsRepository departmentsRepository;

    @Inject
    private RequestsRepository requestsRepository;

    @Inject
    private ExpertInvitationRepository expertInvitationRepository;

    @Inject
    private ExpertsRepository expertsRepository;

    @Inject
    private GroupTemplatesRepository groupTemplatesRepository;

    @Inject
    private SkillsRepository skillsRepository;

    @Inject
    private EntityBanRepository entityBanRepository;

    @Inject
    private MembersRepository membersRepository;

    @Inject
    private SkillSetRepository skillSetRepository;

    @Inject
    private CVRepository cvRepository;

    @Inject
    private ProjsRepository projsRepository;

    @Inject
    private TopicRepository topicRepository;

    @Inject
    private CourseRepository courseRepository;

    @Inject
    private JobRepository jobRepository;


    @Override
    public Result<AllRemoved> handle(RemoveAll command) {
        orgsRepository.deleteAll();
        departmentsRepository.deleteAll();
        skillsRepository.deleteAll();
        entityBanRepository.deleteAll();
        membersRepository.deleteAll();
        requestsRepository.deleteAll();
        expertInvitationRepository.deleteAll();
        expertsRepository.deleteAll();
        groupTemplatesRepository.deleteAll();
        skillSetRepository.deleteAll();
        cvRepository.deleteAll();
        projsRepository.deleteAll();
        topicRepository.deleteAll();
        courseRepository.deleteAll();
        jobRepository.deleteAll();

        return new Result<>(new AllRemoved());
    }
}
