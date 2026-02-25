package it.exprivia.service.impl;

import java.util.List;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import it.exprivia.mapper.InventoryMapper;
import it.exprivia.model.dto.CraftingResponseDTO;
import it.exprivia.model.dto.InventoryDTO;
import it.exprivia.model.entity.Inventory;
import it.exprivia.model.enums.Item;
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

        Inventory newInventory = new Inventory(playerId, item, quantity);

       return repository.findByPlayerIdAndItem(playerId, item)
                        .onItem().ifNotNull().transformToUni(inventory -> {
                            inventory.setQuantity(inventory.getQuantity() + quantity); 
                            return repository.persist(inventory);
                        })
                        .onItem().ifNull().switchTo(repository.persist(newInventory))
                        .map(mapper::toDTO);
        
    }

    @Override
    public Uni<List<InventoryDTO>> getPlayerInventory(Long playerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerInventory'");
    }

    @Override
    public Uni<CraftingResponseDTO> craftItem(Long playerId, Item itemToCraft) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'craftItem'");
    }
    
}
