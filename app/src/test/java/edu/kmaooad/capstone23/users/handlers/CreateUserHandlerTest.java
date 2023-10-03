package edu.kmaooad.capstone23.users.handlers;

import edu.kmaooad.capstone23.common.HandlerTest;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.users.commands.CreateUser;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.events.UserCreated;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateUserHandlerTest extends HandlerTest<User, CreateUser, UserCreated> {
  @Test
  @DisplayName("Should succeed if all fields valid")
  public void shouldSucceedIfPayloadValid() {
    Result<UserCreated> result = run(UserMocks.validUser());

    assertHandled(result);
  }

  @Test
  @DisplayName("Should deny request if first name is invalid")
  public void shouldDenyRequestIfInvalidFirstName() {
    Result<UserCreated> result = run(UserMocks.userWithNoFirstName());

    assertDenied(result);
  }

  @Test
  @DisplayName("Should deny request if first name is exhaustive")
  public void shouldDenyRequestIfExhaustiveFirstName() {
    Result<UserCreated> result = run(UserMocks.userWithExhaustiveFirstName());

    assertDenied(result);
  }


  @Test
  @DisplayName("Should deny request if last name is invalid")
  public void shouldDenyRequestIfInvalidLastName() {
    Result<UserCreated> result = run(UserMocks.userWithNoLastName()
    );

    assertDenied(result);
  }

  @Test
  @DisplayName("Should deny request if last name is exhaustive")
  public void shouldDenyRequestIfExhaustiveLastName() {
    Result<UserCreated> result = run(UserMocks.userWithExhaustiveLastName());

    assertDenied(result);
  }

  @Test
  @DisplayName("Should deny request if email is invalid")
  public void shouldDenyRequestIfInvalidEmail() {
    Result<UserCreated> result = run(UserMocks.userWithInvalidEmail());

    assertDenied(result);
  }

  @Override
  protected void initCommand() {
    command = new CreateUser();
  }

  @Override
  protected void insertPayload(User user) {
    command.setFirstName(user.firstName);
    command.setLastName(user.lastName);
    command.setEmail(user.email);
  }
}
