package it.exprivia.mapper;

import it.exprivia.model.dto.PlayerDTO;
import it.exprivia.model.entity.Player;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface PlayerMapper {

    PlayerDTO toDTO(Player player);

    Player toEntity(PlayerDTO player);

    List<PlayerDTO> toDTOs(List<Player> players);

    List<Player> toEntities(List<PlayerDTO> playerDtos);

}