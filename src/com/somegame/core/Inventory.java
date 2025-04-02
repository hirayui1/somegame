package com.somegame.core;

import com.somegame.core.items.Item;
import java.util.ArrayList;

public class Inventory {
    private final ArrayList<Item> inventory;
    private int gold;

    public Inventory() {
        this.inventory = new ArrayList<>(16);
        fillInventoryWithInitialNulls();
        this.gold = 0;
    }

    public void addGold(int quantity) {
        gold += quantity;
    }

    public boolean add(Item item) {
        for (int i = 0; i < 16; i++) {
            if (inventory.get(i) == null) {
                inventory.set(i, item);
                return true;
            }
        }

        return false;
    }

    public boolean add(Item item, int index) {
        if (index <= 16) {
            if (inventory.get(index) == null) {
                inventory.set(index, item);
                return true;
            }
        }

        return false;
    }

    public void remove(Item item) {
        if (inventory.contains(item)) {
            inventory.remove(item);
        } else {
            System.out.println("You do not have such item.");
        }
    }

    public void replace(int replace, Item item) {
        inventory.set(replace, item);
    }

    public boolean isFull() {
        for (Item i : inventory) {
            if (i == null) {
                return false;
            }
        }
        return true;
    }

    public void listInventory() {
        for (int i = 0, j = 8; i < 8; i++, j++) {
            String item1 = inventory.get(i) != null ? inventory.get(i).toString() : "-";
            String item2 = inventory.get(j) != null ? inventory.get(j).toString() : "-";

            System.out.printf("%2d. %-15s    %2d. %-15s%n", (i+1), item1, (j+1), item2);
        }
    }

    private void fillInventoryWithInitialNulls() {
        for (int i = 0; i < 16; i++) {
            inventory.add(null);
        }
    }

    public Item getSlot(int index) {
        return inventory.get(index);
    }

    public int getSize() {
        return inventory.size();
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
