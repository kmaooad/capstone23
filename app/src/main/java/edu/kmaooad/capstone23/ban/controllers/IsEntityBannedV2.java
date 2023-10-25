package edu.kmaooad.capstone23.ban.controllers;

import edu.kmaooad.capstone23.ban.events.EntityIsBannedV2;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/v2/ban")
@APIResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EntityIsBannedV2.class))})
public class IsEntityBannedV2 extends TypicalController<edu.kmaooad.capstone23.ban.commands.IsEntityBannedV2, EntityIsBannedV2> {
}
