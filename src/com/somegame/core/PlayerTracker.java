package com.somegame.core;

import com.somegame.core.location.Location;

import java.util.ArrayList;

public final class PlayerTracker {
    private static PlayerTracker INSTANCE;
    private final ArrayList<Location> trackPlayer;

    private PlayerTracker() {
        trackPlayer = new ArrayList<>();
    }

    public static PlayerTracker getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerTracker();
        }
        return INSTANCE;
    }

    public Location get(int index) {
        if (index >= 0 && index < trackPlayer.size()) {
            return trackPlayer.get(index);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int size() {
        return trackPlayer.size();
    }

    public void forwardUpdate(Location location) {
        if (location != null) {
            trackPlayer.add(location);
        } else {
            throw new IllegalArgumentException("Cannot add null value to tracker");
        }
    }

    public void backwardUpdate(int index) { // move backward to the chosen location
        if (index >= 0 && index < trackPlayer.size()) {
            trackPlayer.subList(index, trackPlayer.size()).clear();
        }
    }

    public void buildNumeratedListString(String startingMessage) {
        StringBuffer sb = new StringBuffer(); // to avoid flooding the java string pool
        sb.append(startingMessage);
        int i = 0;
        for (Location l : trackPlayer) {
            sb.append("\n").append((++i)).append(". ").append(l.getName()); // create a string of numerated list of cities
        }

        System.out.println(sb);
    }
}