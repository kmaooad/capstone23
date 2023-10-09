package edu.kmaooad.capstone23.members.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.members.commands.CreateMemberByCorpEmail;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/members/create/corp")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = BasicMemberCreated.class)) })
public class CreateMemberByCorpEmailController extends TypicalController<CreateMemberByCorpEmail, BasicMemberCreated> {
}
