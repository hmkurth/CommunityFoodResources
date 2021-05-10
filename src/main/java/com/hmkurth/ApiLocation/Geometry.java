package com.hmkurth.ApiLocation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type Geometry.
 */
public class Geometry{

	@JsonProperty("viewport")
	private Viewport viewport;

	@JsonProperty("bounds")
	private Bounds bounds;

	@JsonProperty("location")
	private Location location;

	@JsonProperty("location_type")
	private String locationType;

    /**
     * Set viewport.
     *
     * @param viewport the viewport
     */
    public void setViewport(Viewport viewport){
		this.viewport = viewport;
	}

    /**
     * Get viewport viewport.
     *
     * @return the viewport
     */
    public Viewport getViewport(){
		return viewport;
	}

    /**
     * Set bounds.
     *
     * @param bounds the bounds
     */
    public void setBounds(Bounds bounds){
		this.bounds = bounds;
	}

    /**
     * Get bounds bounds.
     *
     * @return the bounds
     */
    public Bounds getBounds(){
		return bounds;
	}

    /**
     * Set location.
     *
     * @param location the location
     */
    public void setLocation(Location location){
		this.location = location;
	}

    /**
     * Get location location.
     *
     * @return the location
     */
    public Location getLocation(){
		return location;
	}

    /**
     * Set location type.
     *
     * @param locationType the location type
     */
    public void setLocationType(String locationType){
		this.locationType = locationType;
	}

    /**
     * Get location type string.
     *
     * @return the string
     */
    public String getLocationType(){
		return locationType;
	}
}