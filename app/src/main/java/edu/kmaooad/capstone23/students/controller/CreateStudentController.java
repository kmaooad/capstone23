package edu.kmaooad.capstone23.students.controller;


import edu.kmaooad.capstone23.common.FileController;
import edu.kmaooad.capstone23.students.commands.CreateStudent;
import edu.kmaooad.capstone23.students.events.StudentsCreated;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/students/create_csv")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StudentsCreated.class))})
public class CreateStudentController extends FileController<CreateStudent, StudentsCreated> {
}
