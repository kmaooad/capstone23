import java.util.Optional;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.DeleteDepartment;
import edu.kmaooad.capstone23.departments.events.DepartmentDeleted;
import edu.kmaooad.capstone23.notifications.commands.SendNotificationCommand;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationSourceEvent;
import edu.kmaooad.capstone23.notifications.events.NotificationSent;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;

@Decorator
public class DepartmentDeleteNotificationHandler implements CommandHandler<DeleteDepartment, DepartmentDeleted>{

    @Inject
    @Delegate
    private CommandHandler<DeleteDepartment, DepartmentDeleted> decoratedHandler;

    @Inject
    private CommandHandler<SendNotificationCommand, NotificationSent> notificationHandler;

    @Inject
    private NotificationService notificationService;

	@Override
	public Result<DepartmentDeleted> handle(DeleteDepartment command) {
        Result<DepartmentDeleted> result = decoratedHandler.handle(command);
        
        if(!result.isSuccess()){
            return result;
        }

        Optional<Notification> notification = notificationService.findByIdAndSource(command.getId(), NotificationSourceEvent.DEPARTMENT_DELETED);

        if(notification.isEmpty()){
            return result;
        }

        SendNotificationCommand cmd = new SendNotificationCommand(notification.get());

        notificationHandler.handle(cmd);

        return result;
	}

}
