package it.exprivia.resource;

import java.net.URI;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;

import it.exprivia.model.dto.UserDTO;
import it.exprivia.service.IUserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UserResource {

    @Inject
    IUserService service;

    @POST
    @Operation(summary = "Create a User")
    public Response create(UserDTO dto) { 

        UserDTO created = service.createUser(dto);

        return Response.created(URI.create("/users/" + created.id()))
                       .entity(created)
                       .build(); 
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Find User by id")
    public Response findById(@PathParam("id") Long id) { 

        UserDTO user = service.findById(id);

        return Response.ok(user).build(); // 200 OK
    }

    @GET
    @Operation(summary = "Find all the Users")
    public Response findAll() { 

        List<UserDTO> users = service.findAll();
        
        return Response.ok(users).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a User by id")
    public Response delete(@PathParam("id") Long id) {
        
        service.removeById(id);

        return Response.noContent().build();
    }
}
