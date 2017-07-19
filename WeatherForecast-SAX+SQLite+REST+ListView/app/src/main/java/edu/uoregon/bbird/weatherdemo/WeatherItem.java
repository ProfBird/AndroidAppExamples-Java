package edu.uoregon.bbird.weatherdemo;
// Written by Brian Bird 7/11/15, updated 7/13/17

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class WeatherItem {
    
    private String forecastDate = null;
    private String icon = null;
    private String forecastText = null;
    private String title = null;
    private String period = null;
    private String pop = null;
    
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