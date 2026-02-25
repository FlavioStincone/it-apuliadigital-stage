package it.exprivia.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import it.exprivia.model.entity.Player;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerRepository implements PanacheRepository<Player> {

    public Uni<Player> findByUsername(String username) {

        return find("username", username).firstResult();
    }
}