package it.exprivia.service.impl;

import java.util.List;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import it.exprivia.exceptions.PlayerNotFoundException;
import it.exprivia.mapper.PlayerMapper;
import it.exprivia.model.dto.PlayerResponseDTO;
import it.exprivia.model.dto.PlayerDTO;
import it.exprivia.model.entity.Player;
import it.exprivia.repository.PlayerRepository;
import it.exprivia.service.IPlayerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PlayerServiceImpl implements IPlayerService {

    @Inject
    PlayerRepository repository;

    @Inject
    PlayerMapper mapper;

    @Override
    @WithTransaction
    public Uni<PlayerResponseDTO> createPlayer(PlayerDTO playerDTO) {
        
        Player newPlayer = mapper.toEntity(playerDTO);

        return repository.persist(newPlayer)
                         .onItem().transform(mapper::toDTO);
    }

    @Override
    @WithSession
    public Uni<PlayerResponseDTO> findPlayer(String username) {
       
        return repository.findByUsername(username)
                         .onItem().ifNull().failWith(() -> new PlayerNotFoundException("User " + username + " not found"))
                         .onItem().transform(mapper::toDTO);
    }

    @Override
    @WithSession
    public Uni<List<PlayerResponseDTO>> findAllPlayers() {
        
        return repository.listAll()
                         .onItem().ifNull().failWith(() -> new PlayerNotFoundException("No players in the database"))
                         .onItem().transform(mapper::toDTOs);
   
    }

    @Override
    @WithTransaction
    public Uni<Void> deletePlayer(String username) {

        return repository.findByUsername(username)
                .onItem().ifNull()
                .failWith(() -> new PlayerNotFoundException("User " + username + " not found"))
                .chain(player -> repository.delete(player));
    }
    
}
