package edu.kmaooad.capstone23.students.controller;


import edu.kmaooad.capstone23.common.FileController;
import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.students.commands.CreateStudent;
import edu.kmaooad.capstone23.students.events.StudentCreated;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.RestForm;

import java.io.File;

@Path("/students/create_csv")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StudentCreated.class))})
public class CreateStudentController extends FileController<CreateStudent, StudentCreated> {
}
