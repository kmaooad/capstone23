package edu.kmaooad.capstone23.orgs.departments;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

@Path("/departments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartmentResource {

    @Inject
    MongoClient mongoClient;

    @POST
    @Path("/create")
    public Response create(Department department) {
        MongoCollection<Department> collection = mongoClient.getDatabase("capstone23").getCollection("departments", Department.class);
        collection.insertOne(department);
        return Response.status(Response.Status.CREATED).entity(department).build();
    }

    @POST
    @Path("/update")
    public Response update(Department department) {
        if (department.id == null || department.id.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("ID is required for update").build();
        }
        MongoCollection<Department> collection = mongoClient.getDatabase("capstone23").getCollection("departments", Department.class);
        Bson filter = Filters.eq("_id", new ObjectId(department.id));
        collection.replaceOne(filter, department);
        return Response.ok().entity(department).build();
    }

    @POST
    @Path("/delete")
    public Response delete(String id) {
        MongoCollection<Department> collection = mongoClient.getDatabase("capstone23").getCollection("departments", Department.class);
        Bson filter = Filters.eq("_id", new ObjectId(id));
        collection.deleteOne(filter);
        return Response.ok().entity("Department deleted").build();
    }
}
