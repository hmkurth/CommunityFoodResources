package com.hmkurth.ApiLocation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type Viewport.
 */
public class Viewport{

	@JsonProperty("southwest")
	private Southwest southwest;

	@JsonProperty("northeast")
	private Northeast northeast;

    /**
     * Set southwest.
     *
     * @param southwest the southwest
     */
    public void setSouthwest(Southwest southwest){
		this.southwest = southwest;
	}

    /**
     * Get southwest southwest.
     *
     * @return the southwest
     */
    public Southwest getSouthwest(){
		return southwest;
	}

    /**
     * Set northeast.
     *
     * @param northeast the northeast
     */
    public void setNortheast(Northeast northeast){
		this.northeast = northeast;
	}

    /**
     * Get northeast northeast.
     *
     * @return the northeast
     */
    public Northeast getNortheast(){
		return northeast;
	}
}