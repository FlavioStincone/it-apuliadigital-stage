package it.exprivia.resource;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import io.smallrye.mutiny.Uni;
import it.exprivia.model.dto.PlayerDTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
public interface IPlayerResource {

    @Operation(summary = "create a new player", description = "registers a player in the database and returns his basic profile")
    @APIResponses(value = {
        @APIResponse(
            responseCode = "201", 
            description = "player successfully created"
        ),
        @APIResponse(
            responseCode = "400", 
            description = "invalid input data (e.g. incorrect email)"
        ),
        @APIResponse(
            responseCode = "500",
            description = "Dublicate key (email or username)"
        )
    })
    @Consumes(MediaType.APPLICATION_JSON)
    Uni<Response> createPlayer(@Valid PlayerDTO playerDTO);

    @Operation(summary = "find a player by his Username", description = "search for the player based on the username entered")
    @APIResponses(value = {
        @APIResponse(
            responseCode = "201", 
            description = "player found"
        ),
        @APIResponse(
            responseCode = "404", 
            description = "palyer not found"
        )
    })
    Uni<Response> findPlayer(String username);

    @Operation(summary = "find all players")
    @APIResponses(value = {
        @APIResponse(
            responseCode = "201", 
            description = "players found"
        ),
        @APIResponse(
            responseCode = "404", 
            description = "No players in the Database"
        )
    })
    Uni<Response> findAllPlayers();

    @Operation(summary = "delete a player by his username")
    @APIResponses(value = {
        @APIResponse(
            responseCode = "201", 
            description = "players deleted"
        ),
        @APIResponse(
            responseCode = "404", 
            description = "player not found"
        )
    })
    Uni<Response> deletePlayer(String username);
    
}