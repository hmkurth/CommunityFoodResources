package com.hmkurth.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geometry{

	@JsonProperty("viewport")
	private Viewport viewport;

	@JsonProperty("bounds")
	private Bounds bounds;

	@JsonProperty("location")
	private Location location;

	@JsonProperty("location_type")
	private String locationType;

	public Viewport getViewport(){
		return viewport;
	}

	public Bounds getBounds(){
		return bounds;
	}

	public Location getLocation(){
		return location;
	}

	public String getLocationType(){
		return locationType;
	}
}