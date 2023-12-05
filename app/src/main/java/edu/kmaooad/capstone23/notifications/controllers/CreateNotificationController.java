package edu.kmaooad.capstone23.notifications.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.notifications.commands.CreateNotification;
import edu.kmaooad.capstone23.notifications.events.NotificationCreated;
import jakarta.ws.rs.Path;


@Path("/notifications/create")
public class CreateNotificationController extends TypicalController<CreateNotification, NotificationCreated> {

}

