package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.competences.commands.UpdateTopic;
import edu.kmaooad.capstone23.competences.events.TopicUpdated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/competences/topic/update")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = TopicUpdated.class))})
public class UpdateTopicController extends TypicalController<UpdateTopic, TopicUpdated> {
}
