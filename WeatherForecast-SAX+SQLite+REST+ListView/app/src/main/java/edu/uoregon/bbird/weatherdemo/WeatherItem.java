package edu.uoregon.bbird.weatherdemo;
// Written by Brian Bird 7/11/15, updated 7/13/17

import android.annotation.SuppressLint;

@SuppressLint("SimpleDateFormat")
public class WeatherItem {
    
    private String forecastDate = null;
	private String forecastTime = null;
    private String icon = null;
    private String forecastText = null;
    private String title = null;
    private String period = null;
    private String pop = null;

	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getForecastText() {
		return forecastText;
	}
	public void setForecastText(String forecastText) {
		this.forecastText = forecastText;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	
	public String getPOP() {
		return pop;
	}
	public void setPOP(String pop) {
		this.pop = pop;
	}
}