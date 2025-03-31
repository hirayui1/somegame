package com.somegame.core.characters.player;

import com.somegame.core.characters.Hero;

public class Caster extends Hero {

    public Caster(String name, int hp, int damage) {
        super(name, hp, damage);
    }

    public void hit(Hero target) {
        if (this.getHp() > 0) { // TODO: redundant? this is check is covered in super
            super.hit(target);
            selfHeal();
        }
    }

    public void selfHeal() {
        if (this.getHp() < 1) {
            System.out.println("I am dead.");
        } else {
            int newHp = Math.min(this.getHp() + 5, 10);
            this.setHp(newHp);
            System.out.println("Healing myself for 5, my current hp becomes " + this.getHp());
        }
    }
}