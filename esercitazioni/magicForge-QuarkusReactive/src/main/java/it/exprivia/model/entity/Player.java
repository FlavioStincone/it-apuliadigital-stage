package it.exprivia.model.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "players")
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Player extends PanacheEntity{

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;
    
    private int level = 1;
    private int experience;
    
    public Player(String username, String email){
        this.username = username;
        this.email = email;
    }

    public int addExperience(int xp) {
        this.experience += xp;
        int levelsGained = 0;

        while (this.experience >= 100) {
            this.level += 1;
            this.experience -= 100;
            levelsGained++;
        }

        return levelsGained;
    }
}
