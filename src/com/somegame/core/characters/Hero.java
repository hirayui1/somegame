package com.somegame.core.characters;

import com.somegame.core.Inventory;
import com.somegame.core.items.*;
import com.somegame.core.items.equippable.Armor;
import com.somegame.core.items.equippable.Equippable;
import com.somegame.core.items.equippable.Weapon;
import com.somegame.core.location.Location;
import com.somegame.core.location.Map;

public class Hero extends Character {
    private int experience; // xp cap = 125 * level
    private Weapon weapon;
    private Armor armor;
    private final Inventory inventory;
    private Location location;
    private final Map map;

    public Hero(String name, int hp, int damage, int level) {
        super(name, hp, damage, level);

        inventory = new Inventory();
        map = new Map();

        this.experience = 0;
    }

    public Hero(String name, int hp, int damage) {
        this(name, hp, damage, 1);
    }

    public Hero(String name) {
        this(name, 15, 2);
    }

    @Override
    public void hit(Character target) {
        if (this.hp > 0) {
            target.setHp(Math.max(target.getHp() - calculateDamage(), 0));
            System.out.println(this.getName() + " deals " + calculateDamage() + " damage to " + target.getName() + ", leaving " + target.getHp() + " hp.");

            retaliate(target); // retaliate as long as alive regardless of holding a weapon or not
            onLastHit(target);
        }
    }

    @Override
    public void retaliate(Character target) { // not calling target hit method on this class because that will make the combat autonomous
        if (target.getHp() > 0) {
            this.hp = Math.max(this.getHp() - target.getDamage(), 0);
            System.out.println(target.getName() + " deals " + target.getDamage() + " damage back to " + this.getName() + ", leaving " + this.getHp() + " hp.");
        }
    }

    @Override
    public void takeDamage(int hpLoss) {
        this.hp -= hpLoss; // not sure if this or .setHp();
    }


    private int calculateDamage() {
        int weaponDmg = 0;

        if (weapon != null) {
            weaponDmg = weapon.getDamage();
        }
        return Math.max((damage + weaponDmg), 0);
    }

    public void onLastHit(Character target) {
        if (target.getHp() == 0) {
            gainExperience(target);
            gainLoot();
        }
    }

    private void gainExperience(Character target) {
        setExperience(getExperience() + 25 * target.getLevel());
    }

    private void gainLoot() {
        inventory.addGold(250);
    }

    public void addItem(Item item) {
        if (inventory.add(item)) {
            System.out.println(item.getName() + " has been added to the inventory");
        }
    }
    public void addItem(Item item, int index) {
        if (inventory.add(item, index)) {
            System.out.println(item.getName() + " has been added to the inventory");
        }
    }

    public void listInventory() {
        getInventory().listInventory();
        System.out.println("You have " + getInventory().getGold() + " gold.");
    }

    public void useFromInventory(int index) {
        if (inventory.getSlot(index) instanceof Consumable) { // damage elixir -> +5 damage
            Consumable temp = ((Consumable) inventory.getSlot(index));
            if (temp.getQuantity() > 0) {
                int qty = temp.getQuantity();
                setDamage(getDamage() + temp.getStat());
                if (qty == 1) {
                    inventory.remove(temp);
                    System.out.println("Last " + temp.getName() + " was used.");
                } else {
                    temp.decrease(); // deletes the item after using
                    System.out.println("Consumed " + temp.getName() + ". " + temp.getQuantity() + " left.");
                }
            }
        } else {
            System.out.println("This is not usable");
        }
    }

    public void unequip(Equippable item) {
        if (item instanceof Weapon) {
            if (weapon != null) {
                if (item == weapon) {
                    inventory.add(weapon);
                    setWeapon(null);
                    System.out.println("Unequipped ");
                } else {
                    System.out.println("This item is not currently equipped, therefore you cannot unequip it.");
                }
            } else {
                System.out.println("You do not have a weapon to unequip.");
            }
        } else if (item instanceof Armor) {
            if (armor != null) {
                if (item == armor) {
                    inventory.add(weapon);
                    setArmor(null);
                    System.out.println("Unequipped ");
                } else {
                    System.out.println("This item is not currently equipped, therefore you cannot unequip it.");
                }
            } else {
                System.out.println("You do not have an armor to unequip.");
            }
        }
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Map getMap() {
        return map;
    }
}