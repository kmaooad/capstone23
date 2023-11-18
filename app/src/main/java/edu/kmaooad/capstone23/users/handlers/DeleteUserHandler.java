package edu.kmaooad.capstone23.users.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.users.commands.CreateUser;
import edu.kmaooad.capstone23.users.commands.DeleteUser;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.events.UserCreated;
import edu.kmaooad.capstone23.users.events.UserDeleted;
import edu.kmaooad.capstone23.users.services.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class DeleteUserHandler implements CommandHandler<DeleteUser, UserDeleted> {
    @Inject
    UserService userService;

    @Override
    public Result<UserDeleted> handle(DeleteUser command) {
        Optional<User> userOptional = userService.findByIdOptional(command.getId());

        if (userOptional.isEmpty()) {
            return new Result<UserDeleted>(null, "User not found");
        }


        User user = userOptional.get();
        userService.delete(user);

        UserDeleted deletedUser = new UserDeleted(user.id.toHexString());
        return new Result<UserDeleted>(deletedUser);
    }
}
