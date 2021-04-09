package com.hmkurth.persistence;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmkurth.api.Location;
import com.hmkurth.api.LocationApi;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
@Log4j2

public class ApiTestServiceClient {
    @Test
    public void testgeoJSON() throws Exception {
        Client client = ClientBuilder.newClient();
        Response response =
                client.target("https://google-maps-geocoding.p.rapidapi.com/geocode/json?address=3426%20hargrove%20st%2C%20madison%2C%20wi%2C%2053714&language=en")
                        .request()
                        .header("x-rapidapi-key", "07034781e1msh71ad0a0dad0cfc1p14c692jsn985c94dcb586")
                        .header("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
                        .get();
        String apiResponse = response.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        //ignore entities not declared
       //apper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Location location = mapper.readValue(apiResponse, Location.class);
        log.debug(location.toString());
        Double expectedLocationLatitude =40.71;
        assertEquals(?,location.getLat());
    }
}
   /** var unirest = require("unirest");

    var req = unirest("GET", "https://google-maps-geocoding.p.rapidapi.com/geocode/json");

req.query({
        "address": "164 Townsend St., San Francisco, CA",
        "language": "en"
        });

        req.headers({
        "x-rapidapi-key": "07034781e1msh71ad0a0dad0cfc1p14c692jsn985c94dcb586",
        "x-rapidapi-host": "google-maps-geocoding.p.rapidapi.com",
        "useQueryString": true
        });


        req.end(function (res) {
        if (res.error) throw new Error(res.error);

        console.log(res.body);
        });
    **/