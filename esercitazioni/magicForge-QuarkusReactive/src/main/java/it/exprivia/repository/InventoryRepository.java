package it.exprivia.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;
import it.exprivia.model.entity.Inventory;
import it.exprivia.model.enums.Item;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class InventoryRepository implements PanacheRepository<Inventory> {

    public Uni<List<Inventory>> findByPlayerId(Long playerId) {
        return find("playerId", playerId).list();//in quarkus si scrive direttamente la query  
    }

    public Uni<Inventory> findByPlayerIdAndItem(Long playerId, Item item) {
    return find("playerId = :playerId and item = :item", Parameters.with("playerId", playerId).and("item", item))
           .firstResult();
    }

    /* questo metodo equivale a:
    *   SELECT * 
    *    FROM inventory 
    *    WHERE playerId = $1 AND item = $2;
    */
}