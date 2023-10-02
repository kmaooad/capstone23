package edu.kmaooad.capstone23.communication.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.communication.commands.CreateParticipant;
import edu.kmaooad.capstone23.communication.events.ParticipantCreated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/participants/create")
@APIResponse(responseCode = "200", content = {
    @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = ParticipantCreated.class)
    )}
)
public class CreateParticipantController extends TypicalController<CreateParticipant, ParticipantCreated> {
}
