package it.exprivia.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.exprivia.model.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    
}
