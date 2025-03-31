package com.somegame.core.location;

import com.somegame.core.NPC.Alchemist;
import com.somegame.core.NPC.Vendor;
import com.somegame.core.NPC.Weaponsmith;

import java.util.ArrayList;

public class City extends Location {
    private final ArrayList<Vendor> vendors;
    private City next;
    // TODO: private List<Location> forwardPath; for other places such as monster areas later

    public City(String name) {
        super(name);
        vendors = populateWithVendors();
    }

    public ArrayList<Vendor> populateWithVendors() {
        ArrayList<Vendor> vendors = new ArrayList<>();

        vendors.add(new Weaponsmith("Weaponsmith")); // StarterCity -> Weaponsmith, Alchemist, Armorsmith
        vendors.add(new Alchemist("Alchemist"));

        return vendors;
    }

    public void listVendors() {
        int i = 1;
        for (Vendor vendor : vendors) {
            System.out.println(i + ". " + vendor);
            i++;
        }
    }

    public ArrayList<Vendor> getVendors() {
        return vendors;
    }

    public City getNext() {
        return next;
    }

    public void setNext(City next) {
        this.next = next;
    }
}