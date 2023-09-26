package edu.kmaooad.capstone23.orgs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.orgs.commands.SetHiringStatusOn;
import edu.kmaooad.capstone23.orgs.events.HiringStatusSettedOn;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/orgs/set-hiring-status-on")
@APIResponse(responseCode = "200", content = {
        @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = HiringStatusSettedOn.class)
        )}
)
public class SetHiringStatusOnController extends TypicalController<SetHiringStatusOn, HiringStatusSettedOn> {
}
