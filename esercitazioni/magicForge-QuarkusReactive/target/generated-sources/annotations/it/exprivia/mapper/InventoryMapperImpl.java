package it.exprivia.mapper;

import it.exprivia.model.dto.InventoryDTO;
import it.exprivia.model.entity.Inventory;
import it.exprivia.model.enums.Item;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-25T17:23:23+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260128-0750, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@ApplicationScoped
public class InventoryMapperImpl implements InventoryMapper {

    @Override
    public InventoryDTO toDTO(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }

        Item item = null;
        int quantity = 0;

        String displayName = inventoryItemDisplayName( inventory );
        if ( displayName != null ) {
            item = Enum.valueOf( Item.class, displayName );
        }
        quantity = inventory.getQuantity();

        InventoryDTO inventoryDTO = new InventoryDTO( item, quantity );

        return inventoryDTO;
    }

    @Override
    public List<InventoryDTO> toDTOs(List<Inventory> players) {
        if ( players == null ) {
            return null;
        }

        List<InventoryDTO> list = new ArrayList<InventoryDTO>( players.size() );
        for ( Inventory inventory : players ) {
            list.add( toDTO( inventory ) );
        }

        return list;
    }

    private String inventoryItemDisplayName(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }
        Item item = inventory.getItem();
        if ( item == null ) {
            return null;
        }
        String displayName = item.getDisplayName();
        if ( displayName == null ) {
            return null;
        }
        return displayName;
    }
}
