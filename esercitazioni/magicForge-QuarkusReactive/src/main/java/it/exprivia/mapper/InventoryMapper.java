package it.exprivia.mapper;

import it.exprivia.model.dto.InventoryDTO;
import it.exprivia.model.entity.Inventory;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface InventoryMapper {

    InventoryDTO toDTO(Inventory inventory);

    List<InventoryDTO> toDTOs(List<Inventory> players);

}