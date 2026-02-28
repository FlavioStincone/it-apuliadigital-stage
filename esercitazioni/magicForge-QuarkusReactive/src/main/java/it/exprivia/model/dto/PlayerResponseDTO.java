package it.exprivia.model.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "PlayerResponse", description = "Rappresentazione completa del giocatore")
public record PlayerResponseDTO(
    
    @Schema(description = "Unique id")
    Long id, 
    
    @Schema(description = "the player's username")
    @NotBlank(message = "username cannot be null")
    String username, 
    
    @Schema(description = "the player's email")
    @NotBlank(message = "email cannot be null")
    @Email(message = "The format is not correct")
    String email, 
    
    @Schema(description = "the player's level")
    int level, 
    
    @Schema(description = "Experience, 100 experience makes a level")
    int experience
) {} 