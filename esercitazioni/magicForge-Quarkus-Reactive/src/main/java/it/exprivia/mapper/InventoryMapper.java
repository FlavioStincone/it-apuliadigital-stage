package it.exprivia.mapper;

import it.exprivia.model.Inventory;
import it.exprivia.model.dto.InventoryDTO;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface InventoryMapper {

    @Mapping(source = "item.displayName", target = "item")
    InventoryDTO toDTO(Inventory inventory);

    List<InventoryDTO> toDTOs(List<Inventory> players);

}