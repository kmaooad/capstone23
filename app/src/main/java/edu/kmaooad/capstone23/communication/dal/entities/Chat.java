package edu.kmaooad.capstone23.communication.dal.entities;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "chats")
public class Chat {
  public ObjectId id;
  public String name;
  public String description;
  public AccessType accessType;

  public enum AccessType {
    Private,
    Public
  }
}
