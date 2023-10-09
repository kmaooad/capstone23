package edu.kmaooad.capstone23.communication.mocks;

import edu.kmaooad.capstone23.common.Mocks;
import edu.kmaooad.capstone23.communication.dal.entities.Participant;
import org.bson.types.ObjectId;

public class ParticipantMocks {
  public static Participant makeParticipant(ObjectId chatId, ObjectId userId) {
    Participant participant = new Participant();

    participant.id = Mocks.mockObjectId();
    participant.chatId = chatId;
    participant.userId = userId;

    return participant;
  }

  public static Participant invalidParticipant() {
    Participant participant = new Participant();

    participant.chatId = Mocks.mockObjectId();
    participant.userId = Mocks.mockObjectId();

    return participant;
  }
}
