package it.exprivia.model.dto;

import it.exprivia.model.enums.Item;
import jakarta.validation.constraints.NotNull;

public record InventoryDTO(
    
    @NotNull(message = "item cannot be null")
    Item item, 
    
    @NotNull(message = "quantity cannot be null")
    int quantity
) {}
