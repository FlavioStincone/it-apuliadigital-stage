package it.exprivia.service;

import java.util.List;

import io.smallrye.mutiny.Uni;
import it.exprivia.model.dto.PlayerResponseDTO;
import it.exprivia.model.dto.PlayerDTO;

public interface IPlayerService {
    
    public Uni<PlayerResponseDTO> createPlayer(PlayerDTO playerDTO);

    public Uni<PlayerResponseDTO> findPlayer(String username);

    public Uni<List<PlayerResponseDTO>> findAllPlayers();

    public Uni<Void> deletePlayer(String username);
}
