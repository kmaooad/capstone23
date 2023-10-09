package edu.kmaooad.capstone23.mail.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.mail.commands.MailDistribution;
import edu.kmaooad.capstone23.mail.events.DistributionCompleted;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/mail/distribution")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = DistributionCompleted.class)) })
public class MailDistributionController extends TypicalController<MailDistribution, DistributionCompleted> {
}
