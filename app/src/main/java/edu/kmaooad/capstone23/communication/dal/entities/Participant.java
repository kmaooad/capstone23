package edu.kmaooad.capstone23.communication.dal.entities;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "participants")
public class Participant {
  public ObjectId id;
  public ObjectId chatId;
  public ObjectId userId;
}
