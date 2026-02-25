package it.exprovia.userManager.model.dto;

public record EmployeeDTO( Long id, String name, String email, String pwd, String role) {
    
    public EmployeeDTO(String name, String email, String pwd, String role) {
        this( null, name, email, pwd, role);
    }
}
