package com.somegame.core.items.equippable;

import com.somegame.core.items.Item;

public class Armor extends Item implements Equippable{
    private int defence;

    public Armor(String name, int tier, int defence) {
        super(name, tier, 0);
        this.defence = defence;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }
}