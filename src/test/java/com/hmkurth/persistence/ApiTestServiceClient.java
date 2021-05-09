package com.hmkurth.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmkurth.ApiLocation.Result;
import com.hmkurth.entity.Location;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import java.text.DecimalFormat;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Api test service client.
 */
@Log4j2

public class ApiTestServiceClient {
    /**
     * Testgeo json.
     *
     * @throws Exception the exception
     */
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

    /**
     * Test location api dao.
     *
     * @throws Exception the exception
     */
    @Test
    public void testLocationApiDao () throws Exception {
        LocationApiDao dao = new LocationApiDao();
        GenericDao<Location> ldao = new GenericDao<>(Location.class);
        //get a location to test
        Location locationToMap = ldao.getById(2);
        Location returnedLocation = dao.convertAddressToLatAndLong(locationToMap);
        Double returnedLng = returnedLocation.getLng();
        DecimalFormat df= new DecimalFormat("00.00");
        String format = df.format(returnedLng);
        double finalValue = (Double)df.parse(format) ;
        assertEquals(-89.33, finalValue);//lng for my house, hargrove
    }

    /**
     * Test insert of new info.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsertOfNewInfo () throws Exception {
        LocationApiDao dao = new LocationApiDao();
        GenericDao<Location> ldao = new GenericDao<>(Location.class);
        Location locationToMap = ldao.getById(2);
        dao.convertAddressToLatAndLong(locationToMap);
        ldao.saveOrUpdate(locationToMap);
        Double returnedLng = locationToMap.getLng();
        DecimalFormat df= new DecimalFormat("00.00");
        String format = df.format(returnedLng);
        double finalValue = (Double)df.parse(format) ;
        assertEquals(-89.33,finalValue);
        //todo probably some more tests
    }

    /**
     * Test get near by locations.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     * @param distance  the distance
     */
    @Test
    public void testGetNearByLocations(float latitude, float longitude, float distance) throws Exception {
        LocationApiDao dao = new LocationApiDao();
        GenericDao<Location> ldao = new GenericDao<>(Location.class);
        Location centerPoint = ldao.getById(2);

    }
}