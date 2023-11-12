package edu.kmaooad.capstone23.users.dal.repositories;

import edu.kmaooad.capstone23.users.dal.entities.User;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Email;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {
  public Optional<User> findById(String id) {
    ObjectId parsedId = new ObjectId(id);

    return findByIdOptional(parsedId);
  }

  public Optional<User> findByEmail(@Email String email) {
    PanacheQuery<User> user = find("unique_email", email);

    return user.firstResultOptional();
  }

  public User insert(User user) {
    persist(user);

    return user;
  }

  public void deleteByEmail (String email) {
    delete("unique_email", email);
  }
}
