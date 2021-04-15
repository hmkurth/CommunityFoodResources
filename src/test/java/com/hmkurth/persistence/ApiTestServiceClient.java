package com.hmkurth.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmkurth.ApiLocation.Result;
import com.hmkurth.entity.Location;
import com.hmkurth.entity.MapLocation;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import java.text.DecimalFormat;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
        //ignore entities not declared//// /mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Result location = mapper.readValue(apiResponse, Result.class);
        log.debug(location.toString());
        Double expectedLocationLatitude =43.0932603;
        //need to change results to decimal format to get tests to run withouth the optional wrapper
        Double locationToTest = location.getResults().get(0).getGeometry().getLocation().getLat();
        DecimalFormat df= new DecimalFormat("00.00");
        String format = df.format(locationToTest);
        double finalValue = (Double)df.parse(format) ;

        assertEquals(43.09, finalValue);
    }

    @Test
    public void testLocationApiDao () throws Exception {
        LocationApiDao dao = new LocationApiDao();
        GenericDao<Location> ldao = new GenericDao<>(Location.class);
        //get a location to test
        Location locationToMap = ldao.getById(2);
        MapLocation returnedLocation = dao.convertAddressToLatAndLong(locationToMap);
        Double returnedLng = returnedLocation.getLng();
        DecimalFormat df= new DecimalFormat("00.00");
        String format = df.format(returnedLng);
        double finalValue = (Double)df.parse(format) ;
        assertEquals(-89.33, finalValue);//lng for my house, hargrove
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