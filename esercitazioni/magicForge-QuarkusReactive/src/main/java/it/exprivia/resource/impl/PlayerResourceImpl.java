package it.exprivia.resource.impl;

import io.smallrye.mutiny.Uni;
import it.exprivia.model.dto.PlayerDTO;
import it.exprivia.resource.IPlayerResource;
import it.exprivia.service.IPlayerService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Path("/user")
public class PlayerResourceImpl implements IPlayerResource{

    @Inject
    IPlayerService service;

    @Override
    @POST
    @Path("/create")
    public Uni<Response> createPlayer(PlayerDTO playerDTO) {
        return service.createPlayer(playerDTO)
                      .map(player -> Response.status(Response.Status.CREATED)
                                             .entity(player)
                                             .build());
    }

    @Override
    @GET
    @Path("/{username}")
    public Uni<Response> findPlayer(@PathParam("username") String username) {
        return service.findPlayer(username)
                      .map(player -> Response.ok(player).build());
    }

    @Override
    @GET
    public Uni<Response> findAllPlayers() {
        return service.findAllPlayers()
                      .map(list -> Response.ok(list).build());
    }

    @Override
    @DELETE
    @Path("/delete/{username}")
    public Uni<Response> deletePlayer(@PathParam("username") String username) {
        return service.deletePlayer(username)
                      .map(player -> Response.noContent().build());
    }
    
}
