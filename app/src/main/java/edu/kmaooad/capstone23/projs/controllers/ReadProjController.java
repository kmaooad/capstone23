package edu.kmaooad.capstone23.projs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.projs.dal.Proj;
import edu.kmaooad.capstone23.projs.handlers.ReadProjHandler;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Path("/projs/read")
@Produces(MediaType.APPLICATION_JSON)
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Proj.class)) })
public class ReadProjController extends TypicalController<Void, List<Proj>> {

    @Inject
    ReadProjHandler readProjHandler;

    @GET
    public List<Proj> listProjects() {
        return (List<Proj>) readProjHandler.readProjects();
    }
}
