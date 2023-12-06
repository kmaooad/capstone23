package edu.kmaooad.capstone23.students.controller;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.students.commands.ReadStudent;
import edu.kmaooad.capstone23.students.events.StudentRead;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/students/id")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json",
                schema = @Schema(implementation = StudentRead.class)) })
public class FindStudentByIdController extends TypicalController<ReadStudent, StudentRead> {
}
