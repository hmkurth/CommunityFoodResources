package com.hmkurth.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Array;

public class Location{

	//private Array[] results;
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