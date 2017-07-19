package edu.uoregon.bbird.weatherdemo;
// Written by Brian Bird 7/11/15, updated 7/13/17

import java.util.ArrayList;

public class WeatherItems extends ArrayList<WeatherItem> {
	// Extending ArrayList to facilitate possible future features

	// Default Serial ID
	private static final long serialVersionUID = 1L;
	
	// Info that applies to the whole forecast
	private String zip = "";
	private String city = "";
	
	public String getState() {
		return zip;
	}
	
	public void setState(String zip) {
		this.zip = zip;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
}
