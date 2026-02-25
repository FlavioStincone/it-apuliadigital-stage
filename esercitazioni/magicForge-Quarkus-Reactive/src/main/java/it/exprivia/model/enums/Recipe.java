package it.exprivia.model.enums;

import java.util.Map;
import lombok.Getter;

@Getter
public enum Recipe {

    // 2 Iron Ore -> 1 Iron Ingot
    CRAFT_IRON_INGOT(Item.IRON_INGOT, Map.of(Item.IRON_ORE, 2)),

    // 2 Iron Ingot + 1 Wood -> 1 Iron Sword 
    CRAFT_IRON_SWORD(Item.IRON_SWORD, Map.of(Item.IRON_INGOT, 2,  Item.WOOD, 1));

    private final Item targetItem;
    private final Map<Item, Integer> ingredients;

    // Un solo costruttore, pulitissimo
    Recipe(Item targetItem, Map<Item, Integer> ingredients) {
        this.targetItem = targetItem;
        this.ingredients = ingredients;
    }

    // TODO si potrebbero leggere le ricette da un file JSON esterno
}