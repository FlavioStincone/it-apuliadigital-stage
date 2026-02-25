package it.exprivia.rider_dispatch.model.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import it.exprivia.rider_dispatch.model.enums.RiderStatus;
import it.exprivia.rider_dispatch.model.enums.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RiderDTO(

    @Schema(description = "il Nome del Corriere")
    @NotBlank
    String name,

    @Schema(description = "il Codice Fiscale e Identificativo di ogni Corriere")
    @NotBlank
    String fiscalCode,

    @Schema(description = "Il tipo di veicolo che il Corriere sta utilizzando")
    @NotNull
    VehicleType vehicleType,

    @Schema(description = "Lo stato Attuale del Corriere")
    @NotNull
    RiderStatus riderStatus,

    @Schema(description = "La latitudine della Posizione attuale del Corriere (Latitudine+Longitudine formano la Posizione Precisa)")
    @NotNull
    Double lastLatitude,

    @Schema(description = "La longitudine della Posizione attuale del Corriere (Latitudine+Longitudine formano la Posizione Precisa)")
    @NotNull
    Double lastLongitude,

    @Schema(description = "La Data dell'ultimo Aggiornamento del Corriere")
    @NotNull
    LocalDateTime lastUpdate
) {} 

