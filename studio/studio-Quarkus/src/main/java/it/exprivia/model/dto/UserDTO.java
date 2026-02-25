package it.exprivia.model.dto;

import java.time.LocalDate;

public record UserDTO(Long id, String name, String surname, LocalDate birthDate, String email) {
    
}
