package edu.kmaooad.capstone23.users.repo;

import com.mongodb.MongoException;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class UserRepositoryTest {
    @Inject
    UserRepository userRepository;

    String email;

    @BeforeEach
    void setUp() {
        var user = new User();
        user.lastName = "last";
        user.firstName = "first";
        user.email = (UUID.randomUUID()).toString().concat("@email.com");
        email = user.email;
        userRepository.persist(user);
    }

    @AfterEach
    void cleanUp() {
        userRepository.findByEmail(email).ifPresent(value -> userRepository.deleteById(value.id));
    }

    @Test
    public void testInsertDuplicateEmailWithGeneratedMethodThrowsMongoException() {
        var user = new User();
        user.lastName = "last";
        user.firstName = "first";
        user.email = email;

        assertThrows(MongoException.class, () -> userRepository.persist(user));
    }
}
