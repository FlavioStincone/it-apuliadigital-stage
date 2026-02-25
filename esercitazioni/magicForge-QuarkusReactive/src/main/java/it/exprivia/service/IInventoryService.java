package it.exprivia.service;

import io.smallrye.mutiny.Uni;
import it.exprivia.model.dto.CraftingResponseDTO;
import it.exprivia.model.dto.InventoryDTO;
import it.exprivia.model.enums.Item;

import java.util.List;

public interface IInventoryService {

    Uni<InventoryDTO> addItemToInventory(Long playerId, Item item, int quantity);

    Uni<List<InventoryDTO>> getPlayerInventory(Long playerId);

    Uni<CraftingResponseDTO> craftItem(Long playerId, Item itemToCraft);

}