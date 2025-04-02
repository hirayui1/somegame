package com.somegame.core.NPC;

import com.somegame.core.Inventory;
import com.somegame.core.characters.Hero;
import com.somegame.core.items.Item;

public abstract class Vendor {
    private String name;
    private final Inventory inventory;

    public Vendor(String name) {
        this.name = name;
        inventory = new Inventory();
    }

    public void trade(Hero player, int index) {
        int playerGold = player.getInventory().getGold();
        Item targetItem = this.getInventory().getSlot(index);

        if (playerGold >= targetItem.getPrice()) {
            player.getInventory().setGold(playerGold - targetItem.getPrice());
            player.addItem(targetItem);
        } else {
            System.out.println(targetItem.getName() + " is " + targetItem.getPrice() + " but you have " + playerGold + ". You do not have enough gold.");
        }
    }

    public void trade(Hero player, int index, int replace) {
        int playerGold = player.getInventory().getGold();
        Item targetItem = this.getInventory().getSlot(index);

        if (playerGold >= targetItem.getPrice()) {
            player.getInventory().setGold(playerGold - targetItem.getPrice());
            player.replaceWithItem(targetItem, replace);
        } else {
            System.out.println(targetItem.getName() + " is " + targetItem.getPrice() + " but you have " + playerGold + ". You do not have enough gold.");
        }
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public abstract void populateWithItems();

    @Override
    public String toString() {
        return getName();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}