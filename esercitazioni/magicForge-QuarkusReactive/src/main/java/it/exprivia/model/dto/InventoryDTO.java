package it.exprivia.model.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import it.exprivia.model.enums.Item;
import jakarta.validation.constraints.Positive;

public record InventoryDTO(

    @Schema(description = "the item")
    Item item, 
    
    @Schema(description = "the quantity of the item")
    @Positive(message = "Quantity must be greater than 0")
    int quantity
) {}
