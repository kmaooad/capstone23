package edu.kmaooad.capstone23.users.mocks;

import edu.kmaooad.capstone23.common.Mocks;
import edu.kmaooad.capstone23.users.dal.entities.User;

import java.util.ArrayList;

public class UserMocks extends Mocks {
    public static User validUser() {
        User user = new User();

        user.firstName = "John";
        user.lastName = "Doe";
        user.email = mockValidEmail();
        user.phoneNumber = "0123456789";
        user.notificationTypes = new ArrayList<>();

        return user;
    }

    public static User userWithGivenEmail(String email) {
        User user = new User();

        user.firstName = "John";
        user.lastName = "Doe";
        user.email = email;

        return user;
    }

    public static User userWithNoFirstName() {
        User user = UserMocks.validUser();

        user.firstName = "";

        return user;
    }

    public static User userWithExhaustiveFirstName() {
        User user = UserMocks.validUser();

        user.firstName = mockLongString();

        return user;
    }

    public static User userWithNoLastName() {
        User user = UserMocks.validUser();

        user.lastName = "";

        return user;
    }

    public static User userWithExhaustiveLastName() {
        User user = UserMocks.validUser();

        user.lastName = mockLongString();

        return user;
    }

    public static User userWithInvalidEmail() {
        User user = UserMocks.validUser();

        user.email = mockInvalidEmail();

        return user;
    }
}
