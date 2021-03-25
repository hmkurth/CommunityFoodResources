package com.hmkurth.api;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressComponentsItem{

	@JsonProperty("types")
	private List<String> types;

	@JsonProperty("short_name")
	private String shortName;

	@JsonProperty("long_name")
	private String longName;

	public List<String> getTypes(){
		return types;
	}

	public String getShortName(){
		return shortName;
	}

	public String getLongName(){
		return longName;
	}
}