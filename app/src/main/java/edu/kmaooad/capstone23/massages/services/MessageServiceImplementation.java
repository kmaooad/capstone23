package edu.kmaooad.capstone23.massages.services;


import edu.kmaooad.capstone23.massages.dal.Message;
import edu.kmaooad.capstone23.massages.dal.MessagesRepository;
import jakarta.inject.Inject;

public class MessageServiceImplementation implements MessageService {

    @Inject
    private MessagesRepository messagesRepository;

    @Override
    public Message insert(Message message) {
        return messagesRepository.insert(message);
    }

    @Override
    public void send(Message m) {
        switch (m.event_type) {
            case "sms":
                //send sms
                break;
            case "email":
                //send email
                break;
            case "telegram":
                //send in telegram
                break;
            default:
                break;
        }
    }
}
