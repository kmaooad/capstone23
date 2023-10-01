package edu.kmaooad.capstone23.users.mocks;

import edu.kmaooad.capstone23.users.dal.entities.User;

public class UserMocks {
  public static User validUser() {
    User user = new User();

    user.firstName = "John";
    user.lastName = "Doe";
    user.email = "john.doe@mail.com";

    return user;
  }
}
