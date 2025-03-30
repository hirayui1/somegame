package com.somegame.core.NPC;

import com.somegame.core.items.Item;

public class Alchemist extends Vendor{
    public Alchemist(String name) {
        super(name);
        populateWithItems();
    }

    @Override
    public void populateWithItems() {
        addItem(new Item("Potion", 5, 50));
    }
}
