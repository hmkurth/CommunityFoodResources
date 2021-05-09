package com.hmkurth.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.List;
import java.util.Properties;

@Log4j2
/**
 * This is the data access model for the location api.  it will take in an address and convert to
 * an address with lat and long
 * TODO ERROR handling, check if address exists before trying to map it
 **/

public class LocationApiDao implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());

    String apiKey;
    String apiHost;
    LocationApiDao dao;
    Client client;
    String targetAddress = "https://google-maps-geocoding.p.rapidapi.com/geocode/json";

    /**
     * constructor that loads properties and assigns them to the meta info needed to access the api
     *
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
     *
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

                locationToMap.setLat((float) result.getResults().get(0).getGeometry().getLocation().getLat());
                locationToMap.setLng(result.getResults().get(0).getGeometry().getLocation().getLng());
                log.info("lng, lat");
            }
        } finally {
            return locationToMap;
        }
    }

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
     *TODO make this take in a location, not lat and lng
     * @param latitude
     * @param longitude
     * @return list of ilocations that are in range
     */
    @Transactional
    public List<Object> getNearByLocations(float latitude, float longitude, int page) {
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
        List list = query.list();
        return list;


    }
}
/**
    SELECT z.zip,
        z.primary_city,
        z.latitude, z.longitude,
        p.distance_unit
        * DEGREES(ACOS(LEAST(1.0, COS(RADIANS(p.latpoint))
        * COS(RADIANS(z.latitude))
        * COS(RADIANS(p.longpoint) - RADIANS(z.longitude))
        + SIN(RADIANS(p.latpoint))
        * SIN(RADIANS(z.latitude))))) AS distance_in_km
        FROM zip AS z
        JOIN (   /* these are the query parameters
        SELECT  42.81  AS latpoint,  -70.81 AS longpoint,
        50.0 AS radius,      111.045 AS distance_unit
        ) AS p ON 1=1
        WHERE z.latitude
        BETWEEN p.latpoint  - (p.radius / p.distance_unit)
        AND p.latpoint  + (p.radius / p.distance_unit)
        AND z.longitude
        BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
        AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
        ORDER BY distance_in_km
        LIMIT 1
 */