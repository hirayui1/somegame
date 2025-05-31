package com.somegame.util;

import com.somegame.core.location.City;

public class DataStore {

    public static City createCityList() { // TODO: later get these from a json or something alike? I've already built a json parser, maybe use that
        City starter = new City("Starter City");
        starter.setNext(new City("Second City"));
        starter.getNext().setNext(new City("Third City"));

        return starter; // return starter city
    }
}