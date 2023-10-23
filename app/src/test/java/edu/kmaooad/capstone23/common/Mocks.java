package edu.kmaooad.capstone23.common;

import org.bson.types.ObjectId;

import java.util.UUID;

public class Mocks {
  public static ObjectId mockObjectId() {
    return new ObjectId();
  }

  public static String mockLongString() {
    return "Lorem".repeat(100);
  }

  public static String mockInvalidEmail() {
    return "example@";
  }

  public static String mockValidEmail() {
    return UUID.randomUUID().toString().concat("@mail.com");
  }
}
