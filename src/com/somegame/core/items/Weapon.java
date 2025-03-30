package com.somegame.core.items;

public class Weapon extends Item implements Equippable {
    private int damage;

    public Weapon(String name, int tier, int damage) {
        super(name, tier, 0);
        this.damage = damage * tier;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}