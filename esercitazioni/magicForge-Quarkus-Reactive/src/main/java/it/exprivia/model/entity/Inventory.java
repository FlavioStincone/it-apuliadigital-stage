package it.exprivia.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import it.exprivia.model.enums.Item;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "inventories")
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Inventory extends PanacheEntity {
    
    @Column(nullable = false)
    private Long playerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Item item;

    @Column(nullable = false)
    private int quantity;

    public Inventory(Long playerId, Item item, int quantity){

        this.playerId = playerId;
        this.item = item;
        this.quantity = quantity;

    }

}
