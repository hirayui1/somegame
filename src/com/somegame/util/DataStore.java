package com.somegame.util;

import com.somegame.core.location.City;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataStore {

    public static City createCityList() {
        City starter = new City("Starter City");
        starter.setNext(new City("Second City"));
        starter.getNext().setNext(new City("Third City"));

        return starter; // return starter city
    }

    public static void createLinkedCityList() { // botched idea
    }
}
