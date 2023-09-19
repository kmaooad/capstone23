package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.competences.commands.DeleteTopic;
import edu.kmaooad.capstone23.competences.events.TopicDeleted;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/competences/topic/delete")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = TopicDeleted.class))})
public class DeleteTopicController extends TypicalController<DeleteTopic, TopicDeleted> {
}
