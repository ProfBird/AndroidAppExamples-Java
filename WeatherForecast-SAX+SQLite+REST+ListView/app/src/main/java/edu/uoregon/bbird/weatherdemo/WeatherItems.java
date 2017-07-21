package edu.uoregon.bbird.weatherdemo;
// Written by Brian Bird 7/11/15, updated 7/21/17

import java.util.ArrayList;

public class WeatherItems extends ArrayList<WeatherItem> {
	// Extending ArrayList to facilitate possible future features

	// Default Serial ID
	private static final long serialVersionUID = 1L;
	
	// Info that applies to the whole forecast
	private String state = "";
	private String city = "";
	private String forecastDate = "";
	private String forecastTime = "";
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}


	public void setForecastDate(String forecastDate) {
		this.forecastDate = forecastDate;
	}
	public String getForecastDate() {
		return forecastDate;
	}

	public void setForecastTime(String forecastTime) {
		this.forecastTime = forecastTime;
	}
	public String getForecastTime() {
		return forecastTime;
	}
}
