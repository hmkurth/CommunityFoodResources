package com.hmkurth.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Northeast{

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