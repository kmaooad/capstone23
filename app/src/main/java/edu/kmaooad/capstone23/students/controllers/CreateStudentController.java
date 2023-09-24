package edu.kmaooad.capstone23.students.controllers;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.students.commands.CreateStudent;
import edu.kmaooad.capstone23.students.events.StudentCreated;
import jakarta.ws.rs.Path;

@Path("/students/create")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StudentCreated.class)) })
public class CreateStudentController extends TypicalController<CreateStudent, StudentCreated> {
}
