package it.exprivia.service;

import java.util.List;

import io.smallrye.mutiny.Uni;
import it.exprivia.model.dto.PlayerDTO;

public interface IPlayerService {
    
    public Uni<PlayerDTO> createPlayer(String username, String email);

    public Uni<PlayerDTO> findPlayer(String username);

    public Uni<List<PlayerDTO>> findAllPlayers();

    public Uni<Void> deletePlayer(String username);
}
