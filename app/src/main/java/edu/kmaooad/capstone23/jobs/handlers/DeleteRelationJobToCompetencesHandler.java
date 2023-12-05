package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.DeleteRelateJobToCompetences;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.jobs.events.CompetenceUnrelated;
import edu.kmaooad.capstone23.jobs.service.JobService;
import edu.kmaooad.capstone23.notification.dal.Notification;
import edu.kmaooad.capstone23.notification.event.NotificationCreated;
import edu.kmaooad.capstone23.notification.services.NotificationService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;
@RequestScoped
public class DeleteRelationJobToCompetencesHandler  implements CommandHandler<DeleteRelateJobToCompetences, CompetenceUnrelated> {

    @Inject
    private JobService jobService;
    @Inject
    private NotificationService notificationService;
    @Override
    public Result<CompetenceUnrelated> handle(DeleteRelateJobToCompetences command) {

        Optional<Job> job = jobService.findJobById(command.getJobId().toString());
        if(job.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This job was previously deleted or never existed");

        CompetenceUnrelated result = new CompetenceUnrelated(command.getJobId(), command.getCompetenceId());

        Job j = job.get();
        if (!j.competencesId.contains(command.getCompetenceId()))
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This job doesn't contain this competence");
        j.competencesId.remove(command.getCompetenceId());
        jobService.update(j);

        //notification creation
        Notification notification = new Notification();
        notification.notificationId = new ObjectId();
        notification.notificationAbout = "DeleteRelationJobToCompetences";
        notification.notificationContent = "Було видалено зв'язок";
        notification.sendingProgramToUse = "email";

        notificationService.insert(notification);
        notificationService.sending(notification);

        return new Result<CompetenceUnrelated>(result);
    }
}