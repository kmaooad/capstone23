package edu.kmaooad.capstone23.users.dal.repositories;

import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.interfaces.repositories.UserRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Email;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {
  @Override
  public Optional<User> findById(String id) {
    ObjectId parsedId = new ObjectId(id);

    return findByIdOptional(parsedId);
  }

  @Override
  public Optional<User> findByEmail(@Email String email) {
    PanacheQuery<User> user = find("unique_email", email);

    return user.firstResultOptional();
  }

  @Override
  public User insert(User user) {
    persist(user);

    return user;
  }

  @Override
  public void deleteByEmail (String email) {
    delete("unique_email", email);
  }
}
