package edu.kmaooad.capstone23.ban.controllers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.commands.UnbanEntity;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.ban.events.EntityUnbanned;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/unban")
@APIResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EntityUnbanned.class))})
public class UnbanEntityController extends TypicalController<UnbanEntity, EntityUnbanned> {
}
