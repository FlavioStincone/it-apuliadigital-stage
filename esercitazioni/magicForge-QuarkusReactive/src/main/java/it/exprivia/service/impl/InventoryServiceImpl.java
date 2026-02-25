package it.exprivia.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import it.exprivia.exceptions.EmptyInventoryException;
import it.exprivia.exceptions.InsufficientMaterialsException;
import it.exprivia.mapper.InventoryMapper;
import it.exprivia.model.dto.CraftingResponseDTO;
import it.exprivia.model.dto.InventoryDTO;
import it.exprivia.model.entity.Inventory;
import it.exprivia.model.enums.Item;
import it.exprivia.model.enums.Recipe;
import it.exprivia.repository.InventoryRepository;
import it.exprivia.service.IInventoryService;
import jakarta.inject.Inject;

public class InventoryServiceImpl implements IInventoryService {

    @Inject
    InventoryRepository repository;

    @Inject
    InventoryMapper mapper;

    @Override
    @WithTransaction
    public Uni<InventoryDTO> addItemToInventory(Long playerId, Item item, int quantity) {

       return repository.findByPlayerIdAndItem(playerId, item)
                        .onItem().ifNotNull().transformToUni(inventory -> {
                            inventory.setQuantity(inventory.getQuantity() + quantity); 
                            return repository.persist(inventory);
                        })
                        .onItem().ifNull().switchTo(repository.persist(new Inventory(playerId, item, quantity)))
                        .map(mapper::toDTO);
        
    }

    // TODO Aggiustare il codice (eccezioni e etodo craft item)

    @Override
    public Uni<List<InventoryDTO>> getPlayerInventory(Long playerId) {

        return repository.findByPlayerId(playerId)
                         .map(inventory -> mapper.toDTOs(inventory))
                         .onItem().failWith(() -> new EmptyInventoryException("No Items found, your Inventory is empty"));    }

    @Override
    @WithTransaction
    public Uni<CraftingResponseDTO> craftItem(Long playerId, Recipe recipe) {
        
        return repository.findByPlayerId(playerId)
                    .invoke(inventoryList -> {

                    Map<Item, Inventory> inventoryMap = inventoryList.stream()
                    .collect(Collectors.toMap(Inventory::getItem, inv -> inv));

                    recipe.getIngredients().forEach((reqItem, rqstQuantity) -> {
                    Inventory playerItem = inventoryMap.get(reqItem);

                    if (playerItem == null || playerItem.getQuantity() < rqstQuantity) {
                        throw new InsufficientMaterialsException("Materiali insufficienti per: " + reqItem.getDisplayName());
                    }

                    if(playerItem.getQuantity()-rqstQuantity == 0) {
                        repository.delete(playerItem);
                    }

                playerItem.setQuantity(playerItem.getQuantity() - rqstQuantity);
            });
        })
        .chain(inventoryList -> addItemToInventory(playerId, recipe.getTargetItem(), 1))
        .map(savedItem -> new CraftingResponseDTO(true, "Forgiato con successo!", recipe.getTargetItem().name()))
        .onItem().failWith(() -> new EmptyInventoryException("No Items found, your Inventory is empty"))
;
    }
}
