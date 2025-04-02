package com.somegame.core.manager;

import com.somegame.core.characters.Hero;
import com.somegame.util.DataStore;

import java.util.Scanner;

public class PlayerCreator {
    private PlayerCreator() {
        throw new AssertionError("Instantiating utility class...");
    }

    public static Hero createPlayer(Scanner scan) {
        System.out.println("\nEnter your character's name.");
        Hero player = new Hero(scan.nextLine());
        player.setLocation(DataStore.createCityList());
        player.getInventory().setGold(500);

        return player;
    }
}