package edu.kmaooad.capstone23.profiles.controllers;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import edu.kmaooad.capstone23.profiles.daI.ProfileImageCreate;
import jakarta.ws.rs.Path;

@Path("/profile/create-image")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileImageCreate.class)) })
public class ProfileImageController {
    
}
