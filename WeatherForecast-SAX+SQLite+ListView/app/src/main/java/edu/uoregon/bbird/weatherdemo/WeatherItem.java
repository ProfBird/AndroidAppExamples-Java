package edu.uoregon.bbird.weatherdemo;
// Written by Brian Bird 7/11/15, updated 7/13/17

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class WeatherItem {
    
    private String forecastDate = null;
    private String description = null;
    private String lowTemp = null;
    private String highTemp = null;
    private String nightPrecip	 = null;
    private String dayPrecip = null;
    
    // This is the format used in the weather XML file
    private SimpleDateFormat dateInFormat = 
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");  // <Date>2014-06-28T00:00:00</Date>
    
    // This is the format we want in our output
    private SimpleDateFormat dateOutFormat = 
        new SimpleDateFormat("MM'/'dd', ' EEEE");
    
    
    public String getForecastDateFormatted() {
        try {
            Date date = dateInFormat.parse(forecastDate.trim());
            String forecastDateFormatted = dateOutFormat.format(date);
            return forecastDateFormatted;
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void setForecastDate(String pubDate) {
        this.forecastDate = pubDate;
    }
    
    public String getForecastDate() {
    	return forecastDate;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getNightPrecip() {
		return nightPrecip;
	}

	public void setNightPrecip(String nightPrecip) {
		this.nightPrecip = nightPrecip;
	}
	
	public String getDayPrecip() {
		return nightPrecip;
	}

	public void setDayPrecip(String dayPrecip) {
		this.dayPrecip = dayPrecip;
	}
}