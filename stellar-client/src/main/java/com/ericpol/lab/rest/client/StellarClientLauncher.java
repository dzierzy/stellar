package com.ericpol.lab.rest.client;

import pl.com.sages.stellar.dto.Page;
import pl.com.sages.stellar.entities.Constellation;

public class StellarClientLauncher {

    public static void main(String[] args) {
        StellarClient client = new StellarClient();
        Page<Constellation> page = client.getConstellations(-1,-1);
        System.out.println(page.getItems());
    }
}
