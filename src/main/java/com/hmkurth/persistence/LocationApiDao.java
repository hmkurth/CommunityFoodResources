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
    Location convertAddressToLatAndLong(Location locationToMap) throws JsonProcessingException {
        //take the input location and make parameters
        String street = locationToMap.getStreetAddressOrIntersection();
        String city = locationToMap.getCity();
        String state = locationToMap.getState();
        String zip = locationToMap.getZip();
        String address = street + " " + city + " " + state + " " + zip;

        Response response = client.target(targetAddress)
                .queryParam("address", address)
                .queryParam("language", "en")
                .request()
                .header("x-rapidapi-key", apiKey)
                .header("x-rapidapi-host", apiHost)
                .get();

        try {
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
            response.close();
            return locationToMap;
        }

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



    Code SnippetsResults
    HttpResponse<String> response = Unirest.get("https://google-maps-geocoding.p.rapidapi.com/geocode/json?address=164%20Townsend%20St.%2C%20San%20Francisco%2C%20CA&language=en")
    .header("x-rapidapi-key", "07034781e1msh71ad0a0dad0cfc1p14c692jsn985c94dcb586")
    .header("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
    .asString();




    Client client = ClientBuilder.newClient();

    WebTarget target = client.target("http://commerce.com/customers");

    Response response = target.post(Entity.xml(new Customer("Bill", "Burke)));
    response.close();

    Customer customer = target.queryParam("name", "Bill Burke")
    .request()
    .get(Customer.class);
    client.close();


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
