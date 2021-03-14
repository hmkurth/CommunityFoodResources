package com.hmkurth.Api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location{

	@JsonProperty("lng")
	private double lng;

	@JsonProperty("lat")
	private double lat;

	public double getLng(){
		return lng;
	}

	public double getLat(){
		return lat;
	}
}