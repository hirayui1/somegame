package com.somegame.core.location;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private HashMap<Location, ArrayList<Location>> connections;

    public Map() {
        connections = new HashMap<>();
        populateMap();
    }

    public void populateMap() {
        ArrayList<Location> locations = new ArrayList<>();
        locations.add(new City("Second city"));
        connections.put(new City("Starter City"), locations);
    }

    public HashMap<Location, ArrayList<Location>> getConnections() {
        return connections;
    }

    public void setConnections(HashMap<Location, ArrayList<Location>> connections) {
        this.connections = connections;
    }


}