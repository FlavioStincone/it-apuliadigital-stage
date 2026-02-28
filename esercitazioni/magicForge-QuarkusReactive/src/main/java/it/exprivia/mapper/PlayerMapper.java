package it.exprivia.mapper;

import it.exprivia.model.dto.PlayerResponseDTO;
import it.exprivia.model.dto.PlayerDTO;
import it.exprivia.model.entity.Player;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface PlayerMapper {

    PlayerResponseDTO toDTO(Player player);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "level", ignore = true)
    @Mapping(target = "experience", ignore = true)
    Player toEntity(PlayerDTO player);

    List<PlayerResponseDTO> toDTOs(List<Player> players);

    List<Player> toEntities(List<PlayerDTO> playerDtos);
    
}