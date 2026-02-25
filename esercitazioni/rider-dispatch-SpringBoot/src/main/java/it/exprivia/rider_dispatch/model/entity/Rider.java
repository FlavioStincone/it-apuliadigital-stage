package it.exprivia.rider_dispatch.model.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import it.exprivia.rider_dispatch.model.enums.RiderStatus;
import it.exprivia.rider_dispatch.model.enums.VehicleType;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Rider {

    @Id    
    private String fiscalCode;

    private String name; 

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType; // SCOOTER, BICYCLE, CAR

    @Enumerated(EnumType.STRING)
    private RiderStatus riderStatus; // AVAILABLE, BUSY, OFFLINE, BRESK

    private Double lastLatitude;
    private Double lastLongitude;

    private LocalDateTime lastUpdate;

    public Rider(String name, String fiscalCode, VehicleType vehicleType, RiderStatus riderStatus, Double lastLatitude, Double lastLongitude, LocalDateTime lastUpdate) {

        this.name = name;
        this.fiscalCode = fiscalCode;
        this.vehicleType = vehicleType;
        this.riderStatus = riderStatus;
        this.lastLatitude = lastLatitude;
        this.lastLongitude = lastLongitude;
        this.lastUpdate = lastUpdate;
        
    }
}