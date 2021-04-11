package com.hmkurth.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmkurth.ApiLocation.Result;
import com.hmkurth.entity.Location;
import com.hmkurth.utilities.PropertiesLoader;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Properties;

@Log4j2
//TODO make properties file, read in uri, address in params
public class LocationApiDao implements PropertiesLoader {
    private final Logger logger = LogManager.getLogger(this.getClass());
    String apiKey = null;
    String apiHost = null;
    LocationApiDao dao;
    Client client;
    /**
     * constructor that loads properties
     * @throws Exception
     */
    public LocationApiDao() throws Exception {
        client = ClientBuilder.newClient();
        Properties apiProperties = new Properties();
            apiProperties = loadProperties("/api.properties");
            apiKey = apiProperties.getProperty("x-rapidapi-key");
            apiHost = apiProperties.getProperty("x-rapidapi-host");
            //create the web target
             createWebTarget();
    }
    /**
     * create a reusable web target template for the client builder and
     * instantiates the client builder because there should only be one instance of it per class
     */
    public void createWebTarget(){
        WebTarget target;
        target = client.target("https://google-maps-geocoding.p.rapidapi.com/geocode/json")
                .queryParam("streetAddress", "10")
                .queryParam("city", "json")
                .queryParam("state", "json")
                .queryParam("zip", "metric")
                .queryParam("x-rapidapi-key", apiKey);

    }
    /**
     * a method that takes in a Location object and returns a mappable object/location
     */
    public MapLocation convertAddressToLatAndLong( Location locationToMap) throws JsonProcessingException {
        MapLocation mapLocation = new MapLocation();
        //take the input location and make parameters
        String street = locationToMap.getStreetAddressOrIntersection();
        String city = locationToMap.getCity();
        String state = locationToMap.getState();
        String zip = locationToMap.getZip();

        //build the request
            Response response = client.target("https://google-maps-geocoding.p.rapidapi.com/geocode/json?address=" + street + "%20" + city + "%20" + state + "%20" + zip)
                    .request()
                    .header("x-rapidapi-key", "07034781e1msh71ad0a0dad0cfc1p14c692jsn985c94dcb586")//an error using the apikey from properties
                    .header("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
                    .get();

            String apiResponse = response.readEntity(String.class);
            ObjectMapper mapper = new ObjectMapper();
            Result location = mapper.readValue(apiResponse, Result.class);
            log.debug(location.toString());

            mapLocation.setLat(location.getResults().get(0).getGeometry().getLocation().getLat());
            mapLocation.setLat(location.getResults().get(0).getGeometry().getLocation().getLat());
            logger.info("lng, lat");
            return mapLocation;

        }


    /** sample method
     *
     * @param place
     * @return

    public MapLocation getLocation(String place) {
        return target.queryParam("q", place)
                .request(MediaType.APPLICATION_JSON)
                .get(MapLocation.class);
    }*/
}
   /**
    private Client client;
    private WebTarget target;

    @PostConstruct
    protected void init() {
        client = ClientBuilder.newClient();
        //query params: ?q=Turku&cnt=10&mode=json&units=metric
        target = client.target("http://api.openweathermap.org/data/2.5/forecast/daily")
                .queryParam("cnt", "10")
                .queryParam("mode", "json")
                .queryParam("units", "metric");
    }



    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
            .url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?address=164%20Townsend%20St.%2C%20San%20Francisco%2C%20CA&language=en")
            .get()
            .addHeader("x-rapidapi-key", "07034781e1msh71ad0a0dad0cfc1p14c692jsn985c94dcb586")
            .addHeader("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
            .build();

    Response response = client.newCall(request).execute();
/**
 * https://eclipsesource.com/blogs/2016/04/15/api-key-authentication-in-a-rest-api-with-jax-rs/
 * @Path("/my/resource")
public class AssignLeafletRestService {

@POST @Produces({ MediaType.APPLICATION_JSON })
public String create(@HeaderParam("X-My-API-Key-Token") String token, ...) {
// look up API Key for token
// log API Key usage
// proceed with request
}
The client adds the header when it sends the request.

Entity entity = createEntity();
ClientConfig config = new ClientConfig();
Client client = ClientBuilder.newClient(config);
WebTarget target = client.target(getBaseURI());
Response response = target.path("my").path("resource").request().accept(MediaType.APPLICATION_JSON)
.header("X-My-API-Key-Token", "012345678901234567890123456789ab").post(entity);



https://www.baeldung.com/jersey-sse-client-request-headers
public Response simpleHeader(String headerKey, String headerValue) {
Client client = ClientBuilder.newClient();
WebTarget webTarget = client.target("https://sse.example.org/");
Invocation.Builder invocationBuilder = webTarget.request();
invocationBuilder.header(headerKey, headerValue);
return invocationBuilder.get();
}
And, actually, we can compact this quite nicely for added readability:

public Response simpleHeaderFluently(String headerKey, String headerValue) {
Client client = ClientBuilder.newClient();

return client.target("https://sse.example.org/")
.request()
.header(headerKey, headerValue)
.get();
}
 **/
