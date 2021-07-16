package com.hmkurth.persistence;
/**
public class TestStaticMapAPI {

     * test to see if markers show up on the map.  it produces an image, so not sure how to test that...
     * @throws Exception

    @Test
    public void testStaticMapAPI() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://maps.googleapis.com/maps/api/staticmap?center=43.0731,-89.4012&zoom=12&size=400x400&markers=my%20house%7C43.0932603,%20-89.33&key=AIzaSyCLGoKo1ZhK7TyAsvpPwQZmlLsAQxWnpRM");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        assertEquals("???", response);//returns an image with a point where my house is, not very usefull
    }
}*/
/**
https://maps.googleapis.com/maps/api/staticmap?size=400x400&markers=color:blue	label:A	Brooklyn,NY	Brighton+Beach,NY
        "https://maps.googleapis.com/maps/api/staticmap?center=San%20Francisco,CA&zoom=12&size=600x400&markers=san%20francisco,%20ca|37.811039,%20-122.477567&key=YOUR_API_KEY"
 **/