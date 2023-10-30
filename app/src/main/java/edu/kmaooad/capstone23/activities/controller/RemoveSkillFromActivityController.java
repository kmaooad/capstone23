package edu.kmaooad.capstone23.activities.controller;

import edu.kmaooad.capstone23.activities.commands.RemoveSkillFromActivity;
import edu.kmaooad.capstone23.activities.events.SkillFromActivityRemoved;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;


@Path("/activity/removeskill")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = SkillFromActivityRemoved.class)) })
public class RemoveSkillFromActivityController extends TypicalController<RemoveSkillFromActivity, SkillFromActivityRemoved> {
}