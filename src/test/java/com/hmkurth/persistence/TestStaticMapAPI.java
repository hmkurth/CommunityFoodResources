package com.hmkurth.persistence;
import org.junit.Test;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStaticMapAPI {

    @Test
    public void testStaticMapAPI() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://maps.googleapis.com/maps/api/staticmap?center=43.0731,-89.4012&zoom=12&size=400x400&key=AIzaSyCLGoKo1ZhK7TyAsvpPwQZmlLsAQxWnpRM");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        assertEquals("???", response);//returns an image
    }
}