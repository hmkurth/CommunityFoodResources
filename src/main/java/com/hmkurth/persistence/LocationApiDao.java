package com.hmkurth.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmkurth.api.LocationApi;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

//TODO make properties file, read in uri, address in params
public class LocationApiDao {

    LocationApi getResponse() throws JsonProcessingException {
        String apiKey = "07034781e1msh71ad0a0dad0cfc1p14c692jsn985c94dcb586";
        String host = "google-maps-geocoding.p.rapidapi.com";
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://google-maps-geocoding.p.rapidapi.com/geocode/json?address=3426%20hargrove%20st.,%20madison,%20WIX-RapidAPI-Key:%2007034781e1msh71ad0a0dad0cfc1p14c692jsn985c94dcb586X-RapidAPI-Host:%20google-maps-geocoding.p.rapidapi.comuseQueryString:%20true");
    /**from stackOverFlow
        Invocation.Builder builder = client
                .target(host)
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, apiKey);
        Response response = builder.get();
     */
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        LocationApi locationResponse = null;
        try {
            locationResponse = mapper.readValue(response, LocationApi.class);
        } catch (JsonProcessingException e) {
            //TODO set up logging and write to log
            e.printStackTrace();
        }

        return locationResponse;
    }
}
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
