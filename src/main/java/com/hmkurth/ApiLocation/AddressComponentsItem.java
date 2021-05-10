package com.hmkurth.ApiLocation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * The type Address components item.
 */
public class AddressComponentsItem{

	@JsonProperty("types")
	private List<String> types;

	@JsonProperty("short_name")
	private String shortName;

	@JsonProperty("long_name")
	private String longName;

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
	 * Set short name.
	 *
	 * @param shortName the short name
	 */
	public void setShortName(String shortName){
		this.shortName = shortName;
	}

	/**
	 * Get short name string.
	 *
	 * @return the string
	 */
	public String getShortName(){
		return shortName;
	}

	/**
	 * Set long name.
	 *
	 * @param longName the long name
	 */
	public void setLongName(String longName){
		this.longName = longName;
	}

	/**
	 * Get long name string.
	 *
	 * @return the string
	 */
	public String getLongName(){
		return longName;
	}
}