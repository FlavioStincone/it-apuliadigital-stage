package it.exprovia.userManager.model.dto;

public record ClientDTO( Long id, String name, String email, String pwd, String role) {
    
    public ClientDTO(String name, String email, String pwd, String role) {
        this( null, name, email, pwd, role);
    }
}

