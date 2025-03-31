package com.somegame.core.characters;

public abstract class Character {
    protected String name;
    protected int hp;
    protected int damage;
    protected int level;

    protected Character(String name, int hp, int damage, int level) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;

        this.level = Math.min(level, 1);
    }

    protected abstract void hit(Character target); // should hit the target

    protected abstract void retaliate(Character target); // should make the target hit you

    protected abstract void takeDamage(int hpLoss); // should manipulate this.hp

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
