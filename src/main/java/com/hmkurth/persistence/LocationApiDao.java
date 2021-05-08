package com.hmkurth.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmkurth.ApiLocation.Result;
import com.hmkurth.entity.Location;
import com.hmkurth.utilities.PropertiesLoader;
import lombok.extern.log4j.Log4j2;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.Properties;

@Log4j2
/**
 * This is the data access model for the location api.  it will take in an address and convert to
 * an address with lat and long
 * TODO ERROR handling, check if address exists before trying to map it
 */

public class LocationApiDao implements PropertiesLoader {
    String apiKey = null;
    String apiHost = null;
    LocationApiDao dao;
    Client client;
    String targetAddress = "https://google-maps-geocoding.p.rapidapi.com/geocode/json";
    /**
     * constructor that loads properties and assigns them to the meta info needed to access the api
     * @throws Exception
     */
    public LocationApiDao() throws Exception {
        client = ClientBuilder.newClient();
        Properties apiProperties = new Properties();
            apiProperties = loadProperties("/api.properties");
            apiKey = apiProperties.getProperty("x-rapidapi-key");
            apiHost = apiProperties.getProperty("x-rapidapi-host");
    }

    /**
     * a method that takes in a Location object and returns a mappable object/location, MapLocation
     * TODO break this up more(tried and failed)
     * @return
     */
    public Location convertAddressToLatAndLong(Location locationToMap) throws JsonProcessingException {
        //take the input location and make parameters
        String street = locationToMap.getStreetAddressOrIntersection();
        String city = locationToMap.getCity();
        String state = locationToMap.getState();
        String zip = locationToMap.getZip();
        String address = street + " " + city + " " + state + " " + zip;

        try (Response response = client.target(targetAddress)
                .queryParam("address", address)
                .queryParam("language", "en")
                .request()
                .header("x-rapidapi-key", apiKey)
                .header("x-rapidapi-host", apiHost)
                .get()) {
            if (response.getStatus() == 200) {
                String apiResponse = response.readEntity(String.class);
                ObjectMapper mapper = new ObjectMapper();
                //creates an object from the returned json
                Result result = mapper.readValue(apiResponse, Result.class);
                log.info(result);

                locationToMap.setLat(result.getResults().get(0).getGeometry().getLocation().getLat());
                locationToMap.setLng(result.getResults().get(0).getGeometry().getLocation().getLng());
                log.info("lng, lat");
            }
        } finally {
            return locationToMap;
        }

    }


}
