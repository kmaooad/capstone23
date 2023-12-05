package edu.kmaooad.capstone23.users.services;

import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    public boolean isUserPresent(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.isPresent();
    }

    public Optional<User> findByIdOptional(ObjectId id) {
        return userRepository.findByIdOptional(id);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
