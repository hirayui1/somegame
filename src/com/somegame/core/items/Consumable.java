package com.somegame.core.items;

public class Consumable extends Item {
    private int stat;
    private int quantity;

    public Consumable(String name, int tier, int price, int stat, int quantity) {
        super(name, tier, price);
        this.stat = stat;
        this.quantity = quantity;
    }

    public Consumable(String name, int tier, int stat) {
        this(name, tier, 0, stat, 1);
    }
    public void decrease() {
        setQuantity(getQuantity() - 1);
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
