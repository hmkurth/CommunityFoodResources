package com.hmkurth.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmkurth.Api.Location;
import com.hmkurth.Api.LocationApi;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;

public class LocationApiTest {
    @Test//testing for the northeast bound lat
    public void testLocationApiJSON() throws Exception {
        LocationApiDao dao = new LocationApiDao();
        //for (Location location: dao.getResponse()) {
            System.out.print(dao.getResponse().getGeometry().getBounds().getNortheast().getLat());



        String expectedLocationLat = "43.0933397";
        assertEquals(expectedLocationLat, dao.getResponse().getGeometry().getBounds().getNortheast().getLat());
    }
}
