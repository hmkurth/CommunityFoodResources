package com.hmkurth.ApiLocation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * The type Results item.
 */
public class ResultsItem{

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

	/**
	 * Set formatted address.
	 *
	 * @param formattedAddress the formatted address
	 */
	public void setFormattedAddress(String formattedAddress){
		this.formattedAddress = formattedAddress;
	}

	/**
	 * Get formatted address string.
	 *
	 * @return the string
	 */
	public String getFormattedAddress(){
		return formattedAddress;
	}

	/**
	 * Set types.
	 *
	 * @param types the types
	 */
	public void setTypes(List<String> types){
		this.types = types;
	}

	/**
	 * Get types list.
	 *
	 * @return the list
	 */
	public List<String> getTypes(){
		return types;
	}

	/**
	 * Set geometry.
	 *
	 * @param geometry the geometry
	 */
	public void setGeometry(Geometry geometry){
		this.geometry = geometry;
	}

	/**
	 * Get geometry geometry.
	 *
	 * @return the geometry
	 */
	public Geometry getGeometry(){
		return geometry;
	}

	/**
	 * Set address components.
	 *
	 * @param addressComponents the address components
	 */
	public void setAddressComponents(List<AddressComponentsItem> addressComponents){
		this.addressComponents = addressComponents;
	}

	/**
	 * Get address components list.
	 *
	 * @return the list
	 */
	public List<AddressComponentsItem> getAddressComponents(){
		return addressComponents;
	}

	/**
	 * Set place id.
	 *
	 * @param placeId the place id
	 */
	public void setPlaceId(String placeId){
		this.placeId = placeId;
	}

	/**
	 * Get place id string.
	 *
	 * @return the string
	 */
	public String getPlaceId(){
		return placeId;
	}
}