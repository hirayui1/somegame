package com.somegame.core;

import com.somegame.core.characters.Hero;
import com.somegame.core.location.City;
import com.somegame.util.DataStore;

import java.util.Scanner;

public class TextUI {
    Hero player;
    
    public void run() {
        Scanner scan = new Scanner(System.in);
        int input;

        player = null;


        System.out.println("Enter any integer to start the game, or 0 to exit.");
        while ((input = Integer.parseInt(scan.nextLine())) != 0) {
            if (player == null) {
                player = createHeroPrompt(scan);
                player.setLocation(DataStore.createCityList());
                player.getInventory().setGold(500);
                System.out.println("Welcome, " + player.getName() + ".");
                endPromptIfCity();
            } else {
                if (player.getLocation() instanceof City) { // if currently in city
                    if (input == 1) {
                        player.listInventory();
                    } else if (input == 2) {
                        System.out.println("Please choose a vendor to interact with");
                        ((City) player.getLocation()).listVendors();
                        int vendorIndex = Integer.parseInt(scan.nextLine());
                        showVendorInventory(vendorIndex - 1);
                        System.out.println("Please choose the item you want to buy");
                        int itemIndex = Integer.parseInt(scan.nextLine());
                        buyVendorItem(vendorIndex - 1, itemIndex - 1);
                    } else if (input == 3) {
                        City currentCity = (City)player.getLocation();
                        player.setLocation(currentCity.getNext());
                    }
                    endPromptIfCity();
                } else { // if not currently in city
                    endPrompt();
                }
            }

        }
    }

    private void buyVendorItem(int vendorIndex, int itemIndex) {
        ((City)player.getLocation()).getVendors().get(vendorIndex).trade(player, itemIndex);
    }

    private void showVendorInventory(int index) {
        ((City)player.getLocation()).getVendors().get(index).getInventory().listInventory();
    }

    private void endPrompt() {
        System.out.printf("""
                \nYou're at %s, make a choice!
                1. List your inventory.
                2. Move.
                0. Exit.%n""", player.getLocation().getName());
    }
    private void endPromptIfCity() {
        System.out.printf("""
                \nYou're at %s, make a choice!
                1. List your inventory.
                2. List vendors.
                3. Move.
                0. Exit.%n""", player.getLocation().getName());
    }

    private Hero createHeroPrompt(Scanner scan) {

        System.out.println("""
                \nYou do not have a character saved. Create a new character?
                1. Create your character.
                0. Exit.""");
        if (Integer.parseInt(scan.nextLine()) == 1) {
            System.out.println("\nEnter your character's name.");
            String name = scan.nextLine();
            return new Hero(name);
        }

        System.exit(0);
        return null;
    }
}
