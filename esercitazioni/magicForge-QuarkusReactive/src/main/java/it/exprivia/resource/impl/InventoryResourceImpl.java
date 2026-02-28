package it.exprivia.resource.impl;

import io.smallrye.mutiny.Uni;
import it.exprivia.model.dto.InventoryDTO;
import it.exprivia.model.enums.Recipe;
import it.exprivia.resource.IInventoryResource;
import it.exprivia.service.IInventoryService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Path("/inventory")
public class InventoryResourceImpl implements IInventoryResource{

    @Inject
    IInventoryService service;

    @POST
    @Override
    @Path("/addItem/{playerId}")
    public Uni<Response> addItemToInventory(@PathParam(value = "playerId") Long playerId, @Valid InventoryDTO inventoryDTO) {

        return service.addItemToInventory(playerId, inventoryDTO)
                      .map(response -> Response.status(Response.Status.CREATED)
                                             .entity(response)
                                             .build());
    }

    @GET
    @Override
    @Path("/{playerId}")
    public Uni<Response> getPlayerInventory(@PathParam(value = "playerId") Long playerId) {

        return service.getPlayerInventory(playerId)
                      .map(list -> Response.ok(list).build());
    }

    @POST
    @Override
    @Path("/{playerId}/craft/{recipe}")
    public Uni<Response> craftItem(@PathParam(value = "playerId") Long playerId, @PathParam(value = "recipe") Recipe itemToCraft) {

        return service.craftItem(playerId, itemToCraft)
                      .map(item -> Response.status(Response.Status.CREATED)
                                             .entity(item)
                                             .build());
    }
    
}
