package edu.kmaooad.capstone23.mail.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.mail.commands.MailOrgsReport;
import edu.kmaooad.capstone23.mail.events.OrgsReportMailed;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/mail/orgs/report")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = OrgsReportMailed.class)) })
public class MailOrgsReportController extends TypicalController<MailOrgsReport, OrgsReportMailed> {
}
