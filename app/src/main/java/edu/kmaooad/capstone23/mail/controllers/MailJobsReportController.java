package edu.kmaooad.capstone23.mail.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.mail.commands.MailJobsReport;
import edu.kmaooad.capstone23.mail.events.JobsReportMailed;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/mail/jobs/report")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = JobsReportMailed.class)) })
public class MailJobsReportController extends TypicalController<MailJobsReport, JobsReportMailed> {
}
