package edu.kmaooad.capstone23.messages.services.impl;

import edu.kmaooad.capstone23.messages.commands.SelectUserMessagingSystem;
import edu.kmaooad.capstone23.messages.dal.UserMessageTypesRepository;
import edu.kmaooad.capstone23.messages.dal.UserMessagingSystemRepository;
import edu.kmaooad.capstone23.messages.services.NotifyEventService;
import edu.kmaooad.capstone23.messages.utils.EmailMessagingService;
import edu.kmaooad.capstone23.messages.utils.TelegramMessagingService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NotifyEventServiceImpl implements NotifyEventService {
    @Inject
    UserMessageTypesRepository messageTypesRepository;

    @Inject
    UserMessagingSystemRepository messagingSystemRepository;

    @Inject
    TelegramMessagingService telegramMessagingService;

    @Inject
    EmailMessagingService emailMessagingService;

    @Override
    public void pushEvent(String type, String event) {
        for (var messageTypes :
                messageTypesRepository.listAll()) {

            if (!messageTypes.messageType.equals(type))
                continue;

            for (var userSystems : messagingSystemRepository.findForUser(messageTypes.userId)) {
                switch (userSystems.messageSystemType) {
                    case SelectUserMessagingSystem.SYSTEM_TELEGRAM -> {
                        emailMessagingService.sendMessage(userSystems.messageSystemInfo, event);
                    }
                    case SelectUserMessagingSystem.SYSTEM_EMAIL -> {
                        telegramMessagingService.sendMessage(userSystems.messageSystemInfo, event);
                    }
                }
            }
        }
    }
}
