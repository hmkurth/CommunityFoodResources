package com.hmkurth.ApiLocation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * The type Result.
 */
public class Result{

	@JsonProperty("results")
	private List<ResultsItem> results;

	@JsonProperty("status")
	private String status;

	/**
	 * Set results.
	 *
	 * @param results the results
	 */
	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	/**
	 * Get results list.
	 *
	 * @return the list
	 */
	public List<ResultsItem> getResults(){
		return results;
	}

	/**
	 * Set status.
	 *
	 * @param status the status
	 */
	public void setStatus(String status){
		this.status = status;
	}

	/**
	 * Get status string.
	 *
	 * @return the string
	 */
	public String getStatus(){
		return status;
	}
}