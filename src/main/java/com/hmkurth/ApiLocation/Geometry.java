package com.hmkurth.ApiLocation;

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

	public void setViewport(Viewport viewport){
		this.viewport = viewport;
	}

	public Viewport getViewport(){
		return viewport;
	}

	public void setBounds(Bounds bounds){
		this.bounds = bounds;
	}

	public Bounds getBounds(){
		return bounds;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	public void setLocationType(String locationType){
		this.locationType = locationType;
	}

	public String getLocationType(){
		return locationType;
	}
}