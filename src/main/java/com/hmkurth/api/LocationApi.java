package com.hmkurth.api;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationApi{

	@JsonProperty("formatted_address")
	private String formattedAddress;

	@JsonProperty("types")
	private List<String> types;

	@JsonProperty("geometry")
	private Geometry geometry;

	@JsonProperty("address_components")
	private List<AddressComponentsItem> addressComponents;

	@JsonProperty("place_id")
	private String placeId;

	public String getFormattedAddress(){
		return formattedAddress;
	}

	public List<String> getTypes(){
		return types;
	}

	public Geometry getGeometry(){
		return geometry;
	}

	public List<AddressComponentsItem> getAddressComponents(){
		return addressComponents;
	}

	public String getPlaceId(){
		return placeId;
	}
}