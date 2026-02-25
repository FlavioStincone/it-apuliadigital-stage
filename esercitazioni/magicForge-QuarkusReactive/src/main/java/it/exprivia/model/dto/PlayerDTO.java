package it.exprivia.model.dto;

public record PlayerDTO(
    
    Long id, 
    
    String username, 
    
    String email, 
    
    int level, 
    
    int experience
) {} 
