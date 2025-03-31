package com.somegame.core.characters;

public class Monster extends Character  {

    protected Monster(String name, int hp, int damage, int level) {
        super(name, hp, damage, level);
    }

    @Override
    protected void hit(Character character) {
        if (character instanceof Hero target) { // monster should only ever hit players
            if (this.hp > 0) {
                int finalDamage = Math.max(target.getHp() - (this.damage - target.getArmor().getDefence()), 0);
                target.takeDamage(finalDamage);
                System.out.println(this.getName() + " deals " + finalDamage + " damage to " + target.getName() + ", leaving " + target.getHp() + " hp.");

                retaliate(character);
            }
        }
    }

    @Override
    protected void retaliate(Character character) {
        if (character instanceof Hero target) {
            if (this.hp > 0) {
                int finalDamage = Math.max(this.getHp() - target.getDamage(), 0);
                takeDamage(finalDamage);
                System.out.println(this.getName() + " deals " + finalDamage + " damage to " + target.getName() + ", leaving " + target.getHp() + " hp.");
            }
        }
    }

    @Override
    protected void takeDamage(int hpLoss) {
        this.hp -= hpLoss; // not sure if this or .setHp();
    }
}