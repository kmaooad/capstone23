package edu.kmaooad.capstone23.communication.interfaces.repositories;

import edu.kmaooad.capstone23.communication.dal.entities.Participant;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import java.util.List;

public interface ParticipantRepository extends PanacheMongoRepository<Participant> {
  List<Participant> findAllByChatId(String chatId);

  List<Participant> findByChatAndUserIds(String chatId, String userId);

  Participant insert(Participant participant);
}
