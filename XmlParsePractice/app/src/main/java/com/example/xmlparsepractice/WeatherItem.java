package com.example.xmlparsepractice;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class WeatherItem {
    
    private String lowTemp = null;
    private String highTemp = null;
    private String forecastDate = null;
    
    private SimpleDateFormat dateOutFormat = 
        new SimpleDateFormat("EEEE h:mm a (MMM d)");
    
    private SimpleDateFormat dateInFormat = 
        new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
    
  
    public void setForecastDate(String pubDate) {
        this.forecastDate = pubDate;
    }
    
    public String getForecastDate() {
    	return forecastDate;
    }

	public String getLowTemp() {
		return lowTemp;
	}

	public void setLowTemp(String lowTemp) {
		this.lowTemp = lowTemp;
	}

	public String getHighTemp() {
		return highTemp;
	}

	public void setHighTemp(String highTemp) {
		this.highTemp = highTemp;
	}
}