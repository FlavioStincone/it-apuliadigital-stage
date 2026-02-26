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
    
    @Override
    public Uni<List<InventoryDTO>> getPlayerInventory(Long playerId) {

        return repository.findByPlayerId(playerId)
                         .map(mapper::toDTOs)
                         .onItem().ifNull().failWith(() -> new EmptyInventoryException("You don't have an inventory"))
                         .onItem().transform(items ->{
                            if(items.isEmpty()){
                                throw new EmptyInventoryException("Your inventory is Empty");
                            }
                            return items;
                        });    
    }

    @Override
    @WithTransaction
    public Uni<CraftingResponseDTO> craftItem(Long playerId, Recipe recipe) {

        return repository.findByPlayerId(playerId)
                         .onItem().ifNull().failWith(new EmptyInventoryException("Inventory not found"))
                         .chain(inventoryList -> {

                            if (inventoryList.isEmpty()) {
                                return Uni.createFrom().failure(new EmptyInventoryException("No Items found, your Inventory is empty"));
                            }

                            Map<Item, Inventory> inventoryMap = inventoryList.stream().collect(Collectors.toMap(Inventory::getItem, inv -> inv));
                            Map<Item, Integer> ingredients = recipe.getIngredients();

                            for (Item ingredient : ingredients.keySet()) { //ciciliamo sulel chiavi

                                Integer quantityNeeded = ingredients.get(ingredient);
                                Inventory inventoryItem = inventoryMap.get(ingredient);

                                if (inventoryItem == null || inventoryItem.getQuantity() < quantityNeeded) {
                                    return Uni.createFrom().failure(new InsufficientMaterialsException("amount of " + ingredient.getDisplayName() + " insufficent"));
                                }

                                inventoryItem.setQuantity(inventoryItem.getQuantity() - quantityNeeded);
                            }        
                            // parte del foreach l'ho creato con l'aiuto dell'AI     
            
                            return Uni.createFrom().voidItem();
                        })
                        .chain(() -> addItemToInventory(playerId, recipe.getTargetItem(), 1))
                        .map(savedItem -> new CraftingResponseDTO(true, "Successfully forged!",recipe.getTargetItem().name()));
    }
}
