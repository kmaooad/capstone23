package edu.kmaooad.capstone23.notifications.handler;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import com.google.inject.Inject;
import com.mongodb.assertions.Assertions;
import edu.kmaooad.capstone23.departments.drivers.DepartmentDriver;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.departments.commands.DeleteDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.events.DepartmentDeleted;
import edu.kmaooad.capstone23.notifications.commands.CreateNotificationCommand;
import edu.kmaooad.capstone23.notifications.dal.NotificationChannel;
import edu.kmaooad.capstone23.notifications.dal.NotificationSourceEvent;
import edu.kmaooad.capstone23.notifications.dal.NotificationsRepository;
import edu.kmaooad.capstone23.notifications.events.NotificationCreated;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class DepartmentDeletedNotificationHandler{

    @Inject
    private CommandHandler<DeleteDepartment, DepartmentDeleted> departmentHandler;

    @Inject
    private CommandHandler<CreateNotificationCommand, NotificationCreated> notificationHandler;

    @Inject
    private UserRepository userRepository;
    
    @Inject
    private NotificationsRepository notificationsRepository;
    private String departmentId;

    @Inject
    DepartmentDriver departmentDriver;

    void createDepartment() {
        Department department = departmentDriver.createDepartment();

        departmentId = department.id.toString();
    }

    @Test
    public void deleteDepartmentHandler(){
        User user = new User();
        user.id = new ObjectId();
        user.firstName = "AAA";
        user.lastName = "BBB";
        user.email = "email@gaaa.com";

        userRepository.insert(user);

        CreateNotificationCommand cmd = new CreateNotificationCommand(user.id.toString(), NotificationSourceEvent.DEPARTMENT_DELETED, NotificationChannel.sms);

        var result = notificationHandler.handle(cmd);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertTrue(result.getValue().getNotification().user.equals(user.id));

        createDepartment();

        DeleteDepartment command = new DeleteDepartment();
        command.setId(departmentId);

        var result2 = departmentHandler.handle(command);

        Assertions.assertTrue(result2.isSuccess());
        Assertions.assertTrue(notificationsRepository.findById(result.getValue().getNotification().id).sent);

    }
}
