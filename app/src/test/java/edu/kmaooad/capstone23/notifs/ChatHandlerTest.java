package edu.kmaooad.capstone23.notifs;


import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.communication.commands.CreateParticipant;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.events.ParticipantCreated;
import edu.kmaooad.capstone23.communication.services.ChatService;
import edu.kmaooad.capstone23.notifs.commands.NotifCommand;
import edu.kmaooad.capstone23.notifs.dal.Notif;
import edu.kmaooad.capstone23.notifs.events.NotifEvent;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ChatHandlerTest {
    @Inject
    private CommandHandler<NotifCommand, NotifEvent> notificationHandler;
    @Inject
    private CommandHandler<CreateParticipant, ParticipantCreated> participantHandler;
    @Inject
    private UserRepository userRepository;
    @Inject
    private ChatService chatService;

    @Test
    @DisplayName("Chat notification: Starter test")
    public void addNotificationTest(){
        User user = UserMocks.validUser();
        userRepository.insert(user);

        NotifCommand command = new NotifCommand();
        command.setUserId(user.id.toString());
        command.setNotificationType("Subscribed_to_notifications");
        command.setNotificationMethod("sms");

        notificationHandler.handle(command);

        Chat chat = new Chat();
        chat.name = "New_Chat";
        chat.accessType = Chat.AccessType.Public;
        chat.description = "description";
        chatService.insert(chat);

        CreateParticipant participantCommand = new CreateParticipant();
        participantCommand.setChatId(chat.id.toString());
        participantCommand.setUserId(user.id.toString());
        Assertions.assertTrue(participantHandler.handle(participantCommand).isSuccess());
    }
}