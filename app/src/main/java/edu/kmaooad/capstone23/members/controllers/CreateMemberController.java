package edu.kmaooad.capstone23.members.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.members.commands.CreateMemberBasic;
import edu.kmaooad.capstone23.members.events.CreatedMemberBasic;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/members/create")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = CreatedMemberBasic.class)) })
public class CreateMemberController extends TypicalController<CreateMemberBasic, CreatedMemberBasic> {
}
