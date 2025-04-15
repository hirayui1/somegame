package com.somegame.core.manager;

import com.somegame.core.characters.Hero;
import com.somegame.util.DataStore;

import java.util.Scanner;

public final class PlayerCreator {
    private PlayerCreator() {
        throw new AssertionError("Instantiating utility class...");
    }

    public static Hero createPlayer(Scanner scan) {
        System.out.println("\nEnter your character's name.");
        String name;
        if (!(name = scan.nextLine()).isEmpty()) {
            Hero player = new Hero(name);
            player.setLocation(DataStore.createCityList());
            player.getInventory().setGold(500);

            return player;
        } else {
            throw new IllegalArgumentException("Player name cannot be empty.");
        }

    }
}