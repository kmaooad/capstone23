package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.communication.commands.CreateParticipant;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.events.ParticipantCreated;
import edu.kmaooad.capstone23.communication.services.ChatService;
import edu.kmaooad.capstone23.notifications.commands.CreateNotification;
import edu.kmaooad.capstone23.notifications.events.NotificationCreated;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AddedNotificationHandlerTest {
    @Inject
    private CommandHandler<CreateNotification, NotificationCreated> createNotificationHandler;
    @Inject
    private CommandHandler<CreateParticipant, ParticipantCreated> createParticipantHandler;
    @Inject
    private UserRepository userRepository;
    @Inject
    private ChatService chatService;

    @Test
    @DisplayName("Added Notification: Basic")
    public void addNotificationTest(){
        User user = createTestUser();

        CreateNotification command = new CreateNotification();
        command.setUserId(user.id.toString());
        command.setType("ADDED");
        command.setMethod("SMS");
        createNotificationHandler.handle(command);

        Chat chat = createTestChat();
        CreateParticipant participantCommand = new CreateParticipant();
        participantCommand.setChatId(chat.id.toString());
        participantCommand.setUserId(user.id.toString());

        Assertions.assertTrue(createParticipantHandler.handle(participantCommand).isSuccess());
    }

    private User createTestUser() {
        User user = UserMocks.validUser();
        userRepository.insert(user);
        return user;
    }

    private Chat createTestChat() {
        Chat chat = new Chat();
        chat.name = "CHAT";
        chat.accessType = Chat.AccessType.Public;
        chat.description = "Some description";
        chatService.insert(chat);
        return chat;
    }
}