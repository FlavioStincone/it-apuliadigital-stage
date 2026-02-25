package it.exprivia.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class InsufficientMaterialsException extends WebApplicationException {
    
    public InsufficientMaterialsException(String message) {

        super(message, Response.Status.BAD_REQUEST);
        
    }
}