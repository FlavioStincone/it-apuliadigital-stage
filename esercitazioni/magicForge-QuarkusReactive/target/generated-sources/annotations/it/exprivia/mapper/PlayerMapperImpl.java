package it.exprivia.mapper;

import it.exprivia.model.dto.PlayerDTO;
import it.exprivia.model.entity.Player;
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
public class PlayerMapperImpl implements PlayerMapper {

    @Override
    public PlayerDTO toDTO(Player player) {
        if ( player == null ) {
            return null;
        }

        Long id = null;
        String username = null;
        String email = null;
        int level = 0;
        int experience = 0;

        id = player.id;
        username = player.getUsername();
        email = player.getEmail();
        level = player.getLevel();
        experience = player.getExperience();

        PlayerDTO playerDTO = new PlayerDTO( id, username, email, level, experience );

        return playerDTO;
    }

    @Override
    public Player toEntity(PlayerDTO player) {
        if ( player == null ) {
            return null;
        }

        Player player1 = new Player();

        player1.setEmail( player.email() );
        player1.setExperience( player.experience() );
        player1.setLevel( player.level() );
        player1.setUsername( player.username() );
        player1.id = player.id();

        return player1;
    }

    @Override
    public List<PlayerDTO> toDTOs(List<Player> players) {
        if ( players == null ) {
            return null;
        }

        List<PlayerDTO> list = new ArrayList<PlayerDTO>( players.size() );
        for ( Player player : players ) {
            list.add( toDTO( player ) );
        }

        return list;
    }

    @Override
    public List<Player> toEntities(List<PlayerDTO> playerDtos) {
        if ( playerDtos == null ) {
            return null;
        }

        List<Player> list = new ArrayList<Player>( playerDtos.size() );
        for ( PlayerDTO playerDTO : playerDtos ) {
            list.add( toEntity( playerDTO ) );
        }

        return list;
    }
}
