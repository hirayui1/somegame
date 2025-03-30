package com.somegame.core;

import com.somegame.core.characters.Hero;
import com.somegame.core.location.City;
import com.somegame.core.location.Location;
import com.somegame.util.DataStore;

import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {
    private Hero player;
    private Scanner scan;
    private ArrayList<Location> trackPlayer;
    
    public void run() {
        scan = new Scanner(System.in);
        int input;

        player = null;
        trackPlayer = new ArrayList<>();

        System.out.println("Enter any integer to start the game, or 0 to exit.");
        while ((input = Integer.parseInt(scan.nextLine())) != 0) {
            if (player == null) {
                player = createHeroPrompt();
                player.setLocation(DataStore.createCityList());
                player.getInventory().setGold(500);
                System.out.println("Welcome, " + player.getName() + ".");
                endPromptIfCity();
            } else {
                if (player.getLocation() instanceof City) { // if currently in city
                    if (input == 1) { // TODO: could change this inner if for input variable to switch
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
                        playerMovementPattern(currentCity);
                    }
                    endPromptIfCity();
                } else { // if not currently in city
                    endPrompt();
                }
            }

        }
    }

    private void playerMovementPattern(City currentCity) {
        System.out.println("""
                Which way do you want to move?
                1. Forward.
                2. Backward.
                """);
        int input = Integer.parseInt(scan.nextLine());
        if (input == 1) { // if forward
            trackPlayer.add(currentCity);
            player.setLocation(currentCity.getNext());
        } else if (input == 2) { // if backward
            StringBuffer sb = new StringBuffer(); // to avoid flooding the java string pool
            sb.append("Choose where you want to go:");
            int i = 0;
            for (Location l : trackPlayer) {
                sb.append("\n").append((++i)).append(". ").append(l.getName()); // create a string of numerated list of cities
            }
            System.out.println(sb);
            if ((input = Integer.parseInt(scan.nextLine())) > 0 && input <= trackPlayer.size()) {
                player.setLocation(trackPlayer.get(input-1));
                trackPlayer.subList(input-1, trackPlayer.size()).clear();
            } else {
                System.out.println("Your choice was not one of the valid options.\nGoing back to the city...");
                endPromptIfCity();
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

    private Hero createHeroPrompt() {

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
