package com.hmkurth.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmkurth.ApiLocation.Result;
import com.hmkurth.entity.Location;
import com.hmkurth.utilities.PropertiesLoader;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The type Location api dao.
 */
@Log4j2
/**
 * This is the data access model for the location api.  it will take in an address and convert to
 * an address with lat and long
 * TODO ERROR handling, check if address exists before trying to map it
 **/

public class LocationApiDao implements PropertiesLoader {
List<com.hmkurth.ApiLocation.Location> allLocations = new ArrayList<com.hmkurth.ApiLocation.Location>();
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * The Api key.
     */
    String apiKey;
    /**
     * The Api host.
     */
    String apiHost;
    /**
     * The Client.
     */
    Client client;
    /**
     * The Target address.
     */
    String targetAddress = "https://google-maps-geocoding.p.rapidapi.com/geocode/json";

    /**
     * constructor that loads properties and assigns them to the meta info needed to access the api
     *
     * @throws Exception the exception
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
     *
     * @param locationToMap the location to map
     * @return location location
     */
    public Location convertAddressToLatAndLong(Location locationToMap) {
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
//todo check result status, "OK" means at least one result was returned and address validated
                String status= result.getStatus();

                locationToMap.setLat((float) result.getResults().get(0).getGeometry().getLocation().getLat());
                locationToMap.setLng(result.getResults().get(0).getGeometry().getLocation().getLng());
                log.info("lng, " +result.getResults().get(0).getGeometry().getLocation().getLng());

                logger.debug("status: " + status);
                if(status == "OK")

                allLocations.add(result.getResults().get(0).getGeometry().getLocation());
                logger.debug("arraylist of good locations: " + allLocations.toString());
            }
        } finally {
            return locationToMap;
        }
    }
/**
 * add the location to an array list
 */
    /**
     * Returns an open session from the SessionFactory
     *
     * @return session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();

    }

    /**
     * https://stackoverflow.com/questions/28847954/what-is-the-best-approach-to-find-all-addresses-that-are-in-a-specific-distance
     * TODO make this take in a location, not lat and lng
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     * @param page      the page
     * @return list of ilocations that are in range
     */
    @Transactional
    public List getNearByLocations(float latitude, float longitude, int page) {
        Session sess = getSession();
        Query query = sess.createSQLQuery("SELECT id,(6371 * 2 * ASIN(SQRT(POWER(SIN((:latitude - abs(lat)) * pi()/180 / 2),2) +" +
                "COS(:latitude * pi()/180 ) * COS(abs(lat) * pi()/180) *" +
                "POWER(SIN((:longitude - lng) * pi()/180 / 2), 2))))*1000 as distance " +
                "FROM location HAVING distance < 50 ORDER BY distance");

        query.setParameter("longitude", longitude);
        query.setParameter("latitude", latitude);
        query.setFirstResult((page - 1) * 10);
        query.setMaxResults(10);
        //i think this returns a list of actual distances(in miles?) from location, how do you get the id???
        logger.debug("getNearbyLocations query result:" + query.list());
        return query.list();


    }
}