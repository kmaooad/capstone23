package edu.kmaooad.capstone23.communication.dal.repositories;

import edu.kmaooad.capstone23.communication.dal.entities.Participant;
import edu.kmaooad.capstone23.communication.interfaces.repositories.ParticipantRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class ParticipantRepositoryImpl implements ParticipantRepository {
  @Override
  public List<Participant> findAllByChatId(String chatId) {
    ObjectId parsedChatId = new ObjectId(chatId);

    return list("chatId", parsedChatId);
  }

  @Override
  public List<Participant> findByChatAndUserIds(String chatId, String userId) {
    ObjectId parsedChatId = new ObjectId(chatId);
    ObjectId parsedUserId = new ObjectId(userId);

    Parameters parameters = Parameters
        .with("chatId", parsedChatId)
        .and("userId", parsedUserId);

    return list("chatId = :chatId AND userId = :userId", parameters);
  }

  @Override
  public Participant insert(Participant participant) {
    persist(participant);

    return participant;
  }
}
