package edu.kmaooad.capstone23.users.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.users.commands.CreateUser;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.interfaces.services.UserService;
import edu.kmaooad.capstone23.users.events.UserCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateUserHandler implements CommandHandler<CreateUser, UserCreated> {
  @Inject
  UserService userService;

  private User user;

  @Override
  public Result<UserCreated> handle(CreateUser command) {
    initUser(command);

    userService.insert(user);

    UserCreated createdUser = new UserCreated(user.id.toHexString());

    return new Result<UserCreated>(createdUser);
  }

  private void initUser(CreateUser command) {
    user = new User();

    user.firstName = command.getFirstName();
    user.lastName = command.getLastName();
    user.email = command.getEmailName();
  }
}
