package edu.kmaooad.capstone23.mail.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.mail.commands.MailStudentsReport;
import edu.kmaooad.capstone23.mail.events.StudentsReportMailed;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/mail/students/report")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StudentsReportMailed.class)) })
public class MailStudentsReportController extends TypicalController<MailStudentsReport, StudentsReportMailed> {
}
