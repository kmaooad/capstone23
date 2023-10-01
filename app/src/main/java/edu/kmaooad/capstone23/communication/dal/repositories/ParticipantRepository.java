package edu.kmaooad.capstone23.communication.dal.repositories;

import edu.kmaooad.capstone23.communication.dal.entities.Participant;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import org.bson.types.ObjectId;

import java.util.List;

public class ParticipantRepository implements PanacheMongoRepository<Participant> {
  public List<Participant> findAllByChatId(String chatId) {
    ObjectId parsedChatId = new ObjectId(chatId);

    return list("chatId", parsedChatId);
  }

  public List<Participant> findByChatAndUserIds(String chatId, String userId) {
    ObjectId parsedChatId = new ObjectId(chatId);
    ObjectId parsedUserId = new ObjectId(userId);

    Parameters parameters = Parameters
        .with("chatId", parsedChatId)
        .and("userId", parsedUserId);

    return list("chatId = :chatId AND userId = :userId", parameters);
  }

  public Participant insert(Participant participant) {
    persist(participant);

    return participant;
  }
}
