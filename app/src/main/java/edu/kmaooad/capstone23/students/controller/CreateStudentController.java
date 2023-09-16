package edu.kmaooad.capstone23.students.controller;


import edu.kmaooad.capstone23.common.HandlingError;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.CreateStudent;
import edu.kmaooad.capstone23.students.events.StudentsCreated;
import edu.kmaooad.capstone23.students.handlers.CreateStudentHandler;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/students/create_csv")
public class CreateStudentController {
    @Inject
    CreateStudentHandler commandHandler;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StudentsCreated.class))})
    @APIResponse(responseCode = "400", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HandlingError.class)) })
    @APIResponse(responseCode = "500")
    public Response postCommand(CreateStudent command) {
        try {
            Result<StudentsCreated> result = commandHandler.handle(command);

            if (!result.isSuccess()) {
                return Response.status(400).entity(result.toError()).build();
            }

            return Response.ok(result.getValue(), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }
}
