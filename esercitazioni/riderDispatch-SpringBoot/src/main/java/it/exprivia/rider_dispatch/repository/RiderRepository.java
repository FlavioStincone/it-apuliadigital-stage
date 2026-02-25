package it.exprivia.rider_dispatch.repository;

import it.exprivia.rider_dispatch.model.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, String> {
    Optional<Rider> findByName(String name);
}