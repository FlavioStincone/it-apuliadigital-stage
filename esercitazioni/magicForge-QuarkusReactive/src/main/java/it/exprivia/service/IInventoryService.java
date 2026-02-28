package it.exprivia.service;

import io.smallrye.mutiny.Uni;
import it.exprivia.model.dto.CraftingResponseDTO;
import it.exprivia.model.dto.InventoryDTO;
import it.exprivia.model.enums.Recipe;

import java.util.List;

public interface IInventoryService {

    Uni<InventoryDTO> addItemToInventory(Long playerId, InventoryDTO inventoryDTO);

    Uni<List<InventoryDTO>> getPlayerInventory(Long playerId);

    Uni<CraftingResponseDTO> craftItem(Long playerId, Recipe itemToCraft);

}