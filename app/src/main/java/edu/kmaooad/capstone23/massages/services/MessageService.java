package edu.kmaooad.capstone23.massages.services;


import edu.kmaooad.capstone23.massages.dal.Message;

public interface MessageService {
        Message insert(Message message);
void send(Message m);
}
