package edu.kmaooad.capstone23.removeAll.handlers;

import com.mongodb.client.MongoClient;
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
import org.eclipse.microprofile.config.inject.ConfigProperty;

@RequestScoped
public class RemoveAllHandler implements CommandHandler<RemoveAll, AllRemoved> {

    @ConfigProperty(name = "quarkus.mongodb.database")
    String databaseName;
    @Inject
    MongoClient mongoClient;


    @Override
    public Result<AllRemoved> handle(RemoveAll command) {
        var database = mongoClient.getDatabase(databaseName);
        database.listCollectionNames().forEach(collectionName -> database.getCollection(collectionName).drop());

        return new Result<>(new AllRemoved());
    }
}
