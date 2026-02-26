package it.exprivia.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PlayerDTO(
    
    Long id, 
    
    @NotBlank(message = "username cannot be null")
    String username, 
    
    @NotBlank(message = "email cannot be null")
    @Email(message = "The format is not correct")
    String email, 
    
    int level, 
    
    int experience
) {} 
