package edu.uoregon.bbird.weatherdemo;

import java.util.ArrayList;

public class WeatherItems extends ArrayList<WeatherItem> {
	// Extending ArrayList to facilitate possible future features
	
	// Info that applies to the whole forecast
	private String zip = "";
	private String city = "";
	
	public String getZip() {
		return zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
}
