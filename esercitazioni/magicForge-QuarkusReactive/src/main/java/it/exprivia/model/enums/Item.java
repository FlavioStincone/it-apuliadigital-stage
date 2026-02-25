package it.exprivia.model.enums;

import lombok.Getter;

@Getter
public enum Item {

    // MATERIALI 
    WOOD("Legno"),
    IRON_ORE("Minerale di Ferro"),
    COAL("Carbone"),

    // OGGETTI CRAFTABILI
    IRON_INGOT("Lingotto di Ferro" ),
    IRON_SWORD("Spada di Ferro" );

    private final String displayName;

    Item(String displayName) {

        this.displayName = displayName;
    }
}