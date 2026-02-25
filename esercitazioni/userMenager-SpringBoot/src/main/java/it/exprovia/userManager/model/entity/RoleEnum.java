package it.exprovia.userManager.model.entity;

public enum RoleEnum{
    CLIENT("CLIENT"), 
    EMPLOYEE("EMPLOYEE");
    
    private final String value;
    
    RoleEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}
