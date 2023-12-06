package edu.kmaooad.capstone23.students.controller;

import edu.kmaooad.capstone23.common.HandlingError;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.UpdateStudent;
import edu.kmaooad.capstone23.students.events.StudentsUpdated;
import edu.kmaooad.capstone23.students.handlers.UpdateStudentHandler;
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

@Path("/students/update_csv")
public class UpdateStudentController {
    @Inject
    UpdateStudentHandler commandHandler;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StudentsUpdated.class))})
    @APIResponse(responseCode = "400", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HandlingError.class))})
    @APIResponse(responseCode = "500")
    public Response postCommand(UpdateStudent command) {
        try {
            Result<StudentsUpdated> result = commandHandler.handle(command);

            if (!result.isSuccess()) {
                return Response.status(400).entity(result.toError()).build();
            }

            return Response.ok(result.getValue(), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }


}
