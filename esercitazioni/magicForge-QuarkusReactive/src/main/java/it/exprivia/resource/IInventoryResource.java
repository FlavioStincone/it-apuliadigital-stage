package it.exprivia.resource;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import io.smallrye.mutiny.Uni;
import it.exprivia.model.dto.InventoryDTO;
import it.exprivia.model.enums.Recipe;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Inventory & Crafting", description = "Object management and crafting system")
public interface IInventoryResource {
    
    @Operation(summary = "adds an item to the inventory", description = "adds a certain number of items to the selected player's inventory")
    @APIResponses(value = {
        @APIResponse(
            responseCode = "201", 
            description = "item added successfully"
        ),
        @APIResponse(
            responseCode = "400",
            description = "invalid input data (e.g. incorrect quantity or item)"
        )
    })
    @Consumes(MediaType.APPLICATION_JSON)
    Uni<Response> addItemToInventory(Long playerId, @Valid InventoryDTO inventoryDTO);

    @Operation(summary = "get all the inventory", description = "Gets all the selected player's inventory")
    @APIResponses(value = {
        @APIResponse(
            responseCode = "201", 
            description = "inventory found"
        ),
        @APIResponse(
            responseCode = "404",
            description = "inventory not found or empty"
        ),
        @APIResponse(
            responseCode = "400",
            description = "item not found"
        )
    })
    Uni<Response> getPlayerInventory(Long playerId);

    @Operation(summary = "build an item", description = "build an item from those available (IRON_INGOT, IRON_SWORD)")
    @APIResponses(value = {
        @APIResponse(
            responseCode = "201", 
            description = "item successfully built"
        ),
        @APIResponse(
            responseCode = "404",
            description = "inventory not found or empty"
        ),
        @APIResponse(
            responseCode = "400",
            description = "insufficient materials or recipe not found"
        )
    })
    @Consumes(MediaType.APPLICATION_JSON)
    Uni<Response> craftItem(Long playerId, Recipe itemToCraft);

}
