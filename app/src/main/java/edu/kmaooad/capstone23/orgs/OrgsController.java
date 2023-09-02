package edu.kmaooad.capstone23.orgs;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/orgs")
public class OrgsController {

    @Inject
    private CreateOrgHandler createOrgHandler;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String createOrg() {
        return "Hello from RESTEasy Reactive";
    }
}
