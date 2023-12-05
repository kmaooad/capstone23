package edu.kmaooad.capstone23.proffesors.notifications.create;

import edu.kmaooad.capstone23.notifications.service.NotificationService;
import edu.kmaooad.capstone23.proffesors.events.ProffesorCreated;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class CreateProfessorNotificationService extends NotificationService<ProffesorCreated> { }
