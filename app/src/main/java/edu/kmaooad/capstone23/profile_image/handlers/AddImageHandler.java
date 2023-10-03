package edu.kmaooad.capstone23.profile_image.handlers;

import org.bson.types.ObjectId;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.profile_image.commands.AddImage;
import edu.kmaooad.capstone23.profile_image.events.ImageAdded;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class AddImageHandler implements CommandHandler<AddImage, ImageAdded> {

     @Inject
     private UserRepository usersRepository;

     public Result<ImageAdded> handle(AddImage command) {
        ObjectId userId = command.getUserId();
        String imageBase64String = command.getImageBase64();
        User user = usersRepository.findById(userId);

        if (user == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "User not found");
        }

        user.imageBase64 = imageBase64String;


        return new Result<ImageAdded>(new ImageAdded(user.id.toString()));
    }
 } 