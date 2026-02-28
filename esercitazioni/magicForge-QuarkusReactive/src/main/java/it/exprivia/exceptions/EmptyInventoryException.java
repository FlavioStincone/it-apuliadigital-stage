package it.exprivia.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class EmptyInventoryException extends WebApplicationException {

    public EmptyInventoryException(String message) {

        super(message, Response.Status.NOT_FOUND);
        
    }
    
}
