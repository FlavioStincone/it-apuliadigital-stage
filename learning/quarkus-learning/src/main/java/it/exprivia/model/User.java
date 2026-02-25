package it.exprivia.model;

import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User extends PanacheEntity { //PanacheEntity mi permette di creare un id (Long) e di autoincrementarlo

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, unique = true)
    private String email;

    public User(String name, String surname, LocalDate birthDate, String email){

        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;

    }
}
