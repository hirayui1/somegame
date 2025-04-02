package com.somegame.core;

import com.somegame.core.NPC.Vendor;
import com.somegame.core.characters.Hero;
import com.somegame.core.location.City;
import com.somegame.core.manager.PlayerCreator;

import java.util.Scanner;

public class TextUI {
    private static TextUI INSTANCE;
    private Hero player;
    private final Scanner scan;
    private final PlayerTracker tracker;

    private TextUI() {
        scan = new Scanner(System.in);
        tracker = PlayerTracker.getInstance();
    } // singleton constructor

    public static TextUI getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TextUI();
        }
        return INSTANCE;
    }

    public void run() {
        int input;

        System.out.println("Enter any integer to start the game, or 0 to exit.");
        while ((input = Integer.parseInt(scan.nextLine())) != 0) {
            try {
                if (player == null) {
                    createHeroPrompt();
                } else {
                    if (player.getLocation() instanceof City) { // if currently in city
                        if (input == 1) { // TODO: could change this if condition chain to a switch expression, and have default be the error prompt?
                            player.listInventory();
                        } else if (input == 2) {
                            System.out.println("Please choose a vendor to interact with");
                            City tempPlayerCity = ((City) player.getLocation());
                            tempPlayerCity.listVendors();
                            int vendorIndex;
                            if ((vendorIndex = Integer.parseInt(scan.nextLine())) <= tempPlayerCity.getVendors().size() && vendorIndex >= 0) { // checks if the user chose one of the vendors
                                showVendorInventory(vendorIndex - 1);
                                System.out.println("Please choose the item you want to buy");
                                int itemIndex = Integer.parseInt(scan.nextLine());
                                buyVendorItem(vendorIndex - 1, itemIndex - 1);
                            } else {
                                System.out.println("Not a valid vendor choice, please try one of the presented vendors.");
                            }
                        } else if (input == 3) {
                            City currentCity = (City) player.getLocation();
                            playerMovementPattern(currentCity);
                        }
                        endPromptIfCity();
                    } else { // if not currently in city
                        endPrompt();
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Not a valid choice, please choose one of the given options.");
                if (player.getLocation() instanceof City) {
                    endPromptIfCity();
                } else {
                    endPrompt();
                }
            }
        }
    }

    private void playerMovementPattern(City currentCity) {
        if (tracker.size() == 0) {
            System.out.println("""
                    Which way do you want to move?
                    1. Forward.""");
        } else if (currentCity.getNext() == null) {
            System.out.println("""
                    Which way do you want to move?
                    2. Backward.""");
        } else {
            System.out.println("""
                    Which way do you want to move?
                    1. Forward.
                    2. Backward.""");
        }

        int input = Integer.parseInt(scan.nextLine());
        if (currentCity.getNext() != null && input == 1) { // if forward
            tracker.forwardUpdate(currentCity);
            player.setLocation(currentCity.getNext());
        } else if (tracker.size() > 0 && input == 2) { // if backward
            try {
                tracker.buildNumeratedListString("Choose where you want to go:");
                input = Integer.parseInt(scan.nextLine());
                player.setLocation(tracker.get(input - 1));
                tracker.backwardUpdate(input - 1); // update the list to be (0, n-1)
            } catch (IllegalArgumentException e) {
                System.out.println("Your choice was not one of the valid options.\nGoing back to the city...");
            }
        } else {
            throw new IllegalArgumentException();
        }

    }

    private void buyVendorItem(int vendorIndex, int itemIndex) {
        Vendor vendor = ((City) player.getLocation()).getVendors().get(vendorIndex);
        try {
            if (!player.invIsFull()) {
                vendor.trade(player, itemIndex);
            } else {
                System.out.println("""
                        Your inventory is full. Choose:
                        1. Choose an item to replace with your current purchase.
                        0. Cancel.""");
                int input = Integer.parseInt(scan.nextLine());
                if (input == 1) {
                    player.listInventory();
                    input = Integer.parseInt(scan.nextLine());
                    vendor.trade(player, itemIndex, input-1);
                } else {
                    System.out.println("Not a valid option.");
                }
            }
        } catch (NullPointerException e) { // might throw NPE if player chooses an empty slot
            System.out.println("The vendor does not have an item to offer in this slot.");
        }
    }

    private void showVendorInventory(int index) {
        ((City) player.getLocation()).getVendors().get(index).getInventory().listInventory();
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

    private void createHeroPrompt() {
        System.out.println("""
                \nYou do not have a character saved. Create a new character?
                1. Create your character.
                0. Exit.""");
        if (Integer.parseInt(scan.nextLine()) == 1) {
            player = PlayerCreator.createPlayer(scan);
            System.out.println("Welcome, " + player.getName() + ".");
            endPromptIfCity();
        } else {
            System.exit(0);
        }
    }
}
