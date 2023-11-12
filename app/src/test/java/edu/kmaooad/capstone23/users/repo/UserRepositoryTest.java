package edu.kmaooad.capstone23.users.repo;

import com.mongodb.MongoException;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.interfaces.services.UserService;
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
    UserService userService;

    String email;

    @BeforeEach
    void setUp() {
        var user = new User();
        user.lastName = "last";
        user.firstName = "first";
        user.email = (UUID.randomUUID()).toString().concat("@email.com");
        email = user.email;
        userService.persist(user);
    }

    @AfterEach
    void cleanUp() {
        userService.findByEmail(email).ifPresent(value -> userService.deleteById(value.id));
    }

    @Test
    public void testInsertDuplicateEmailWithGeneratedMethodThrowsMongoException() {
        var user = new User();
        user.lastName = "last";
        user.firstName = "first";
        user.email = email;

        assertThrows(MongoException.class, () -> userService.persist(user));
    }
}
