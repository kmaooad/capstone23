package edu.kmaooad.capstone23.notification.controller;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.notification.commands.CreateNotification;
import edu.kmaooad.capstone23.notification.event.NotificationCreated;
import jakarta.ws.rs.Path;

@Path("/notification/create")
public class CreateNotificationController extends TypicalController<CreateNotification, NotificationCreated> {
}
