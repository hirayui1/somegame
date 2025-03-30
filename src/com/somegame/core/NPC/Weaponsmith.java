package com.somegame.core.NPC;

import com.somegame.core.items.Item;

public class Weaponsmith extends Vendor {

    public Weaponsmith(String name) {
        super(name);
        populateWithItems();
    }

    @Override
    public void populateWithItems() {
        addItem(new Item("Apple", 3, 20));

    }
}
