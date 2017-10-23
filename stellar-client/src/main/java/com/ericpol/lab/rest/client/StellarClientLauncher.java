package com.ericpol.lab.rest.client;

import pl.com.sages.stellar.dto.Page;
import pl.com.sages.stellar.entities.Constellation;

import javax.ws.rs.core.Response;

public class StellarClientLauncher {

    public static void main(String[] args) throws Exception {
        StellarClient client = new StellarClient();
        Page<Constellation> page = client.getConstellations(-1,-1);
        System.out.println(page.getItems());

        String uploadedFilePath = "./stellar-client/src/main/resources/meteoryt.png"; // TODO align the path to png file which will be uploaded
        Response r = client.upload(uploadedFilePath);
        System.out.println("uploaded file: " + uploadedFilePath + ". server response:" + r);
    }
}
