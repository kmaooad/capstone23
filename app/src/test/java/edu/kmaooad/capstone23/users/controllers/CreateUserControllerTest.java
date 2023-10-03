package edu.kmaooad.capstone23.users.controllers;

import edu.kmaooad.capstone23.common.ControllerTest;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateUserControllerTest extends ControllerTest<User> {
  CreateUserControllerTest() {
    super("/users/create");
  }

  @Test
  @DisplayName("Should succeed if all fields valid")
  public void shouldSucceedIfPayloadValid() {
    assertRequestSucceeds(
        UserMocks.validUser()
    );
  }

  @Test
  @DisplayName("Should deny request if first name is invalid")
  public void shouldDenyRequestIfInvalidFirstName() {
    assertRequestFails(
        UserMocks.userWithNoFirstName()
    );
  }

  @Test
  @DisplayName("Should deny request if first name is exhaustive")
  public void shouldDenyRequestIfExhaustiveFirstName() {
    assertRequestFails(
        UserMocks.userWithExhaustiveFirstName()
    );
  }


  @Test
  @DisplayName("Should deny request if last name is invalid")
  public void shouldDenyRequestIfInvalidLastName() {
    assertRequestFails(
        UserMocks.userWithNoLastName()
    );
  }

  @Test
  @DisplayName("Should deny request if last name is exhaustive")
  public void shouldDenyRequestIfExhaustiveLastName() {
    assertRequestFails(
        UserMocks.userWithExhaustiveFirstName()
    );
  }

  @Test
  @DisplayName("Should deny request if email is invalid")
  public void shouldDenyRequestIfInvalidEmail() {
    assertRequestFails(
        UserMocks.userWithInvalidEmail()
    );
  }
}
