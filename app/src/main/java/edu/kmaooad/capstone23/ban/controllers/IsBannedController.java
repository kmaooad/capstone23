package edu.kmaooad.capstone23.ban.controllers;

import edu.kmaooad.capstone23.ban.commands.IsEntityBanned;
import edu.kmaooad.capstone23.ban.events.EntityIsBanned;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;


@Path("/is-banned")
@APIResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EntityIsBanned.class))})
public class IsBannedController extends TypicalController<IsEntityBanned, EntityIsBanned> {
}
