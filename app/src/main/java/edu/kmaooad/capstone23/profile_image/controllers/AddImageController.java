package edu.kmaooad.capstone23.profile_image.controllers;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.profile_image.commands.AddImage;
import edu.kmaooad.capstone23.profile_image.events.ImageAdded;
import jakarta.ws.rs.Path;

@Path("/users/uploadprofileimage")
@APIResponse(responseCode = "200", content = {
@Content(mediaType = "application/json", schema = @Schema(implementation = AddImage.class)) })
public class AddImageController extends TypicalController<AddImage, ImageAdded> {
    
}
