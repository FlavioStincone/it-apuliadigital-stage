package it.exprivia.model.dto;

import it.exprivia.model.enums.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record InventoryDTO(
    
    @NotBlank(message = "Item cannot be null")
    Item item, 
    
    @NotBlank(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be greater than 0")
    int quantity
) {}
