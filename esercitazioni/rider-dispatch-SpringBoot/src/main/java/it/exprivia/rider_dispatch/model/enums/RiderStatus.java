package it.exprivia.rider_dispatch.model.enums;

public enum RiderStatus {
    AVAILABLE,   // Pronto a ricevere ordini
    BUSY,        // Sta consegnando
    OFFLINE,     // Fine turno o app chiusa
    BREAK        // In pausa
}