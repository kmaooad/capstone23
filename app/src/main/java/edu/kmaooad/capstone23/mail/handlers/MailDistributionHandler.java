package edu.kmaooad.capstone23.mail.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.mail.commands.MailDistribution;
import edu.kmaooad.capstone23.mail.dal.TypeOfDistribution;
import edu.kmaooad.capstone23.mail.events.DistributionCompleted;
import edu.kmaooad.capstone23.mail.service.Notification;
import edu.kmaooad.capstone23.mail.service.NotificationMailService;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.proffesors.dal.Proffesor;
import edu.kmaooad.capstone23.proffesors.dal.ProffesorsRepository;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class MailDistributionHandler implements CommandHandler<MailDistribution, DistributionCompleted> {

    @Inject
    private StudentRepository studentRepository;

    @Inject
    private OrgsRepository orgsRepository;

    @Inject
    private MembersRepository membersRepository;

    @Inject
    private ProffesorsRepository proffesorsRepository;

    @Inject
    private UserRepository userRepository;

    private List<String> getEmailList(TypeOfDistribution target){
        if (target == TypeOfDistribution.STUDENTS){
            List<Student> students = studentRepository.listAll();
            return students.stream().map(entity -> entity.email).collect(Collectors.toList());
        } else if (target == TypeOfDistribution.ORGS) {
            List<Org> orgs = orgsRepository.listAll();
            return orgs.stream().map(entity -> entity.emailDomain).collect(Collectors.toList());
        } else if (target == TypeOfDistribution.MEMBERS) {
            List<Member> members = membersRepository.listAll();
            return members.stream().map(entity -> entity.email).collect(Collectors.toList());
        } else if (target == TypeOfDistribution.PROFESSORS) {
            List<Proffesor> proffesors = proffesorsRepository.listAll();
            return proffesors.stream().map(entity -> entity.email).collect(Collectors.toList());
        } else if (target == TypeOfDistribution.USERS) {
            List<User> users = userRepository.listAll();
            return users.stream().map(entity -> entity.email).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @Inject
    private NotificationMailService service;

    private static final String subject = "Information update";
    private String notificationBody = "";
    private Notification notification = null;

    public Result<DistributionCompleted> handle(MailDistribution command){
        notificationBody = command.getMessageBody();

        TypeOfDistribution target = command.getDistributionTarget();
        List<String> emails = getEmailList(target);
        if (emails == null || emails.size() == 0) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Incorrect type of distribution or repo is empty!");
        }

        for (int i = 0; i < emails.size(); i++){
            notification = new Notification(emails.get(i), subject, notificationBody);
            service.sendNotification(notification);
        }

        DistributionCompleted result = new DistributionCompleted(command.getMessageBody(), command.getDistributionTarget());
        return new Result<>(result);
    }
}