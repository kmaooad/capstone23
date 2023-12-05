package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.communication.commands.CreateParticipant;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.events.ParticipantCreated;
import edu.kmaooad.capstone23.communication.services.ChatService;
import edu.kmaooad.capstone23.notifications.commands.AddNotification;
import edu.kmaooad.capstone23.notifications.events.NotificationAdded;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class AddedToChatNotificationHandlerTests {
    @Inject
    private CommandHandler<AddNotification, NotificationAdded> notificationHandler;
    @Inject
    private CommandHandler<CreateParticipant, ParticipantCreated> participantHandler;
    @Inject
    private UserRepository userRepository;
    @Inject
    private ChatService chatService;
    @Test
    @DisplayName("Added to chat notification: Basic")
    public void addNotificationTest(){
        User user = UserMocks.validUser();
        userRepository.insert(user);
        AddNotification command = new AddNotification();
        command.setUserId(user.id.toString());
        command.setType("ADDED_TO_CHAT");
        command.setSendingMethod("SMS");
        notificationHandler.handle(command);
        Chat chat = new Chat();
        chat.name = "Newchat";
        chat.accessType = Chat.AccessType.Public;
        chat.description = "description";
        chatService.insert(chat);
        CreateParticipant participantCommand = new CreateParticipant();
        participantCommand.setChatId(chat.id.toString());
        participantCommand.setUserId(user.id.toString());
        Assertions.assertTrue(participantHandler.handle(participantCommand).isSuccess());
    }
}
