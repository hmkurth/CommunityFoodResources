package com.hmkurth.ApiLocation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location{

	@JsonProperty("lng")
	private double lng;

	@JsonProperty("lat")
	private double lat;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("name")
	private String name;

	public void setLng(double lng){
		this.lng = lng;
	}

	public float getLng(){
		return lng;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}
}