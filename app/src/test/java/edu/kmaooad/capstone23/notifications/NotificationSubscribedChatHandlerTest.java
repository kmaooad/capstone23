package edu.kmaooad.capstone23.notifications;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.communication.commands.CreateParticipant;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.events.ParticipantCreated;
import edu.kmaooad.capstone23.communication.services.ChatService;
import edu.kmaooad.capstone23.notifications.commands.SubscribeNotifications;
import edu.kmaooad.capstone23.notifications.events.NotificationSubscribed;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class NotificationSubscribedChatHandlerTest {
    @Inject
    private CommandHandler<CreateParticipant, ParticipantCreated> participantHandler;
    @Inject
    private CommandHandler<SubscribeNotifications, NotificationSubscribed> notificationHandler;
    @Inject
    private UserRepository userRepository;
    @Inject
    private ChatService chatService;

    @Test
    @DisplayName("Subscribed for chat notifications test")
    public void addNotificationTest() {
        User user = UserMocks.validUser();
        userRepository.insert(user);

        Chat chat = new Chat();
        chat.name = "Standard chat name";
        chat.accessType = Chat.AccessType.Public;
        chat.description = "Standard description";
        chatService.insert(chat);

        SubscribeNotifications command = new SubscribeNotifications();
        command.setUserId(user.id.toString());
        command.setNotificationStatus("Subscribed_on_notifications");
        command.setSubscriptionMethod("email");
        notificationHandler.handle(command);


        CreateParticipant participantCommand = new CreateParticipant();
        participantCommand.setChatId(chat.id.toString());
        participantCommand.setUserId(user.id.toString());

        Assertions.assertTrue(participantHandler.handle(participantCommand).isSuccess());
    }
}
