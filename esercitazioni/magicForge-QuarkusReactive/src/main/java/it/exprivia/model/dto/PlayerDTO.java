package it.exprivia.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record PlayerDTO(
    
    Long id, 
    
    @NotNull(message = "username cannot be null")
    String username,
    
    @NotNull(message = "email cannot be null")
    @Email(message = "La email deve essere valida")
    String email, 
    
    int level, 
    
    int experience
) {} 
