package it.exprivia.service.impl;

import java.util.List;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import it.exprivia.exceptions.PlayerNotFoundException;
import it.exprivia.mapper.PlayerMapper;
import it.exprivia.model.dto.PlayerDTO;
import it.exprivia.model.entity.Player;
import it.exprivia.repository.PlayerRepository;
import it.exprivia.service.IPlayerService;
import jakarta.inject.Inject;

public class PlayerServiceImpl implements IPlayerService {

    @Inject
    PlayerRepository repository;

    @Inject
    PlayerMapper mapper;

    @Override
    @WithTransaction
    public Uni<PlayerDTO> createPlayer(String username, String email) {
        
        Player newPlayer = new Player(username, email);

        return repository.persist(newPlayer).onItem().transform(player -> mapper.toDTO(player));
    }

    @Override
    public Uni<PlayerDTO> findPlayer(String username) {
       
        return repository.findByUsername(username)
                         .onItem().ifNull().failWith(() -> new PlayerNotFoundException("User " + username + " not found"))
                         .onItem().transform(mapper::toDTO);
    }

    @Override
    public Uni<List<PlayerDTO>> findAllPlayers() {
        
        return repository.listAll()
                         .onItem().ifNull().failWith(() -> new PlayerNotFoundException("No players in the database"))
                         .onItem().transform(mapper::toDTOs);
   
    }

    @Override
    @WithTransaction
    public Uni<PlayerDTO> deletePlayer(String username) {
        
        return repository.findByUsername(username)
                         .onItem().ifNull().failWith(() -> new PlayerNotFoundException("User " + username + " not found"))
                         .chain(player -> repository.delete(player)
                         .replaceWith(mapper.toDTO(player)));
        
    }
    
}
