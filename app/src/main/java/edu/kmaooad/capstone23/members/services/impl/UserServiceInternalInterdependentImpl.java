package edu.kmaooad.capstone23.members.services.impl;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.members.dto.UserDTO;
import edu.kmaooad.capstone23.members.services.UserService;
import edu.kmaooad.capstone23.users.commands.CreateUser;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.events.UserCreated;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class UserServiceInternalInterdependentImpl implements UserService {

    @Inject
    CommandHandler<CreateUser, UserCreated> createUserHandler;

    @Inject
    UserRepository userRepository;

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        return userRepository.findByEmail(email).map(user -> new UserDTO(user.id));
    }

    @Override
    public UserDTO createUser(String firstName, String lastName, String email) {
        var createUserCommand = new CreateUser();
        createUserCommand.setEmail(email);
        createUserCommand.setFirstName(firstName);
        createUserCommand.setLastName(lastName);

        return new UserDTO(new ObjectId(createUserHandler.handle(createUserCommand).getValue().getId()));
    }
}
