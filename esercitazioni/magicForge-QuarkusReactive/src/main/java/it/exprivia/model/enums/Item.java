package it.exprivia.model.enums;

import lombok.Getter;

@Getter
public enum Item {

    // MATERIALI 
    WOOD("wood"),
    IRON_ORE("iron ore"),
    COAL("coal"),

    // OGGETTI CRAFTABILI
    IRON_INGOT("iron ingot" ),
    IRON_SWORD("iron sword" );

    private final String displayName;

    Item(String displayName) {

        this.displayName = displayName;
    }
}