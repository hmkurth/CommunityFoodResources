package com.hmkurth.ApiLocation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type Location.
 */
public class Location{

	@JsonProperty("lng")
	private double lng;

	@JsonProperty("lat")
	private double lat;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
		return name;
	}

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("name")
	private String name;

    /**
     * Set lng.
     *
     * @param lng the lng
     */
    public void setLng(double lng){
		this.lng = lng;
	}

    /**
     * Get lng float.
     *
     * @return the float
     */
    public float getLng(){
		return (float) lng;
	}

    /**
     * Set lat.
     *
     * @param lat the lat
     */
    public void setLat(double lat){
		this.lat = lat;
	}

    /**
     * Get lat double.
     *
     * @return the double
     */
    public double getLat(){
		return lat;
	}
}