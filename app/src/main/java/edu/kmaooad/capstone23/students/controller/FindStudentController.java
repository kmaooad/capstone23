package edu.kmaooad.capstone23.students.controller;


import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.students.commands.FindStudent;
import edu.kmaooad.capstone23.students.events.StudentsFound;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/search/students")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StudentsFound.class))
})
public class FindStudentController extends TypicalController<FindStudent, StudentsFound> {}
