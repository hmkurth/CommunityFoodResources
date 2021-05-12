package com.hmkurth.ApiLocation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type Southwest.
 */
public class Southwest{

	@JsonProperty("lng")
	private double lng;

	@JsonProperty("lat")
	private double lat;

    /**
     * Set lng.
     *
     * @param lng the lng
     */
    public void setLng(double lng){
		this.lng = lng;
	}

    /**
     * Get lng double.
     *
     * @return the double
     */
    public double getLng(){
		return lng;
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