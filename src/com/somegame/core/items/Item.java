package com.somegame.core.items;

public class Item {
    private String name;
    private int tier;
    private int price;

    public Item(String name, int tier, int price) {
        this.name = name;
        this.tier = tier;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "t" + tier + " " + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}