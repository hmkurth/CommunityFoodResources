package com.hmkurth.persistence;

public class GeoMapApi {
    key https://google-maps-geocoding.p.rapidapi.com/geocode/json
}


    /**
     * from the google maps geocoding website:
     *

    var unirest = require("unirest");

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
     */

        req.end(function (res) {
        if (res.error) throw new Error(res.error);

        console.log(res.body);
        });