package it.exprivia.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class PlayerNotFoundException extends WebApplicationException {
    
    public PlayerNotFoundException(String message) {

        super(message, Response.Status.NOT_FOUND); 
        
    }
}