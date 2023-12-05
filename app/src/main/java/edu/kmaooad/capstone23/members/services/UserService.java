package edu.kmaooad.capstone23.members.services;

import edu.kmaooad.capstone23.members.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    Optional<UserDTO> findByEmail(String email);

    UserDTO createUser(String firstName, String lastName, String email);
}
