package edu.kmaooad.capstone23.users.interfaces.services;

import edu.kmaooad.capstone23.users.interfaces.repositories.UserRepository;

public interface UserService extends UserRepository {
  boolean isUserPresent(String id);
}
