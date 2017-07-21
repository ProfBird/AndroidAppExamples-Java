package edu.uoregon.bbird.weatherdemo;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class WeatherItem {
    
    private String lowTemp = null;
    private String highTemp = null;
    private String forecastDate = null;
    private String description = null;
    private String precipitation = null;
    private String symbol = null;
    
    // This is the format used in the weather XML file
    private SimpleDateFormat dateInFormat = 
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");  // Example: 2017-07-21T01:11:57
    
    // This is the format we want in our output
    private SimpleDateFormat dateOutFormat = 
            new SimpleDateFormat("EEEE', ' MMM d', 'hh a");
    
    
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

    public void setPrecipitation(String precipitation) {this.precipitation = precipitation;}
    public String getPrecipitation() {return precipitation;}

    public void setSymbol(String symbol) {this.symbol = symbol;}
    public String getSymbol() {return symbol;}
}