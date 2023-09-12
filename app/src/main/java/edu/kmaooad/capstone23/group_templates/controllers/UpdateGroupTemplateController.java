package edu.kmaooad.capstone23.group_templates.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.group_templates.commands.UpdateGroupTemplate;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateUpdated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/grouptemplates/update")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = GroupTemplateUpdated.class)) })
public class UpdateGroupTemplateController extends TypicalController<UpdateGroupTemplate, GroupTemplateUpdated> {
}
