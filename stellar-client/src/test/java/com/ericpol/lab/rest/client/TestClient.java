package com.ericpol.lab.rest.client;

import com.ericpol.lab.rest.skymap.dto.ListWrapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * Created by xdzm on 2015-08-07.
 */
public class TestClient {

    //@Test
    public void testGetConstellations(){
        ListWrapper list = new Client().getConstellations(2, 3);
        Assert.assertNotNull("list wrapper can not be null", list);
        Assert.assertNotNull("list can not be null", list.getList());
    }

    //@Test
    public void testUploadLogo() throws FileNotFoundException {
        Object result = new Client().upload("c:/upload.png");
        Assert.assertNotNull("message can not be null", result);
    }

}
