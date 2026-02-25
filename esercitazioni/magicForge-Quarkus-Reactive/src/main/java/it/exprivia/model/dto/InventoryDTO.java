package it.exprivia.model.dto;

import it.exprivia.model.enums.Item;

public record InventoryDTO(Item item, int quantity) {}
