package edu.kmaooad.capstone23.members.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.members.commands.GetAllMembers;
import edu.kmaooad.capstone23.members.events.MembersListed;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/members/get/all")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = MembersListed.class))})
public class GetAllMembersController extends TypicalController<GetAllMembers, MembersListed> {
}
